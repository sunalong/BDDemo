# encoding: utf-8
"""
@version: v1.0
@author: lizhonghu
@license: Apache Licence
@contact: 754154377@qq.com
@site:
@software: PyCharm
@file: ExportExcel.py
@time: 2018/5/3 12:01
"""
import sys
import xlsxwriter
import prestodb
import math
import datetime

def ExportExcel():
    print(str(0) + ": " + sys.argv[0])
    print(str(1) + ": " + sys.argv[1])
    # 文件名
    file_name = "common"
    # 日期
    date_str = datetime.datetime.now().strftime('%Y-%m-%d')
    # Excel 名称
    excel_name = "./Excel/"+file_name+"common"+date_str+".xlsx"
    # Excel sheet名称
    worksheet_name = "sheet1"
    # sql文件的路径
    # sql_dir = './ql/'+file_name+'.sql'
    sql_dir = sys.argv[1]
    rows  = []
    # 打开excel配置项
    with open(sql_dir, 'r+', encoding='utf8') as f:
        # sql_list = f.read().split(';')[:-1]
        sql_item = f.read()
        # sql_list = [x.replace('\n', ' ') if '\n' in x else x for x in sql_list]

    # for sql_item in sql_list:
    print(sql_item)
    conn = prestodb.dbapi.connect(host='ycbi-db-01', port=8989, user='sqoop', catalog='hive', schema='ods')
    cur = conn.cursor()
    cur.execute(sql_item)
    # 返回结果集存放到元组中
    rows = cur.fetchall()
    # 定义表头信息，返回表头信息
    table_head = []
    for col in cur.description:
        table_head.append(col[0])

    # 返回结果的行数
    row_cnt = len(rows)
    # 创建excel文件与工作集sheet
    workbook = xlsxwriter.Workbook(excel_name)
    # 结果集大小，Excel最大行数 1048576， 超过的话 重新建立sheet
    sheet_cnt = math.ceil(row_cnt/1048575)
    max_cnt = 1048576
    # 创建sheet页
    for sheet in range(0, sheet_cnt):
        # 创建excel的sheet
        copy_sheet = "_副本%s"%(sheet+1)
        worksheet = workbook.add_worksheet(worksheet_name+copy_sheet)
        # 在excel的sheet中写入表头
        worksheet.write_row('A1', table_head)
        # Excel 最大行数
        for i in range(0, max_cnt):
            index = i+max_cnt*sheet
            if row_cnt >= index+1:
                worksheet.write_row("A%s"%(i+2),rows[index])
            else:
                break
    workbook.close()

if __name__ == '__main__':
    ExportExcel()
    print("导出excell成功")