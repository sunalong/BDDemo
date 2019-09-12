#! /bin/bash -x

declare -r curr_dir=$(cd `dirname $0`; pwd)

sql_file=$1
sql_file="/data/soft/data_platform_export/sql/"${sql_file}

#export PYTHONPATH=$PYTHONPATH
source /data/soft/py3__env/bin/activate
python ${curr_dir}/ExportExcel.py ${sql_file} &>${curr_dir}/export.log
deactivate
