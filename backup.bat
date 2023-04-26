Batch script for Windows PostgreSQL Backup

@echo off
SET day=%date:~0,2%
SET month=%date:~3,2%
SET year=%date:~6,4%
SET hh=%time:~0,2%
SET mm=%time:~3,2%

SET BACKUPDIR=D:\PGDB_Backup
SET datestr=%day%-%month%-%year%_%hh%-%mm%
SET dir=%day%-%month%-%year%
mkdir D:\PGDB_Backup%dir%

#Provide database name here
SET db1=dbname1
SET db2=dbname2

echo datestr is %datestr%

SET BACKUP_FILE1=D:\PGDB_Backup%dir%%db1%%datestr%.sql
SET FILENAME1=%db1%%datestr%.sql

SET BACKUP_FILE2=D:\PGDB_Backup%dir%%db2%%datestr%.sql
SET FILENAME2=%db2%%datestr%.sql

ECHO Backup file name is %FILENAME1% %FILENAME2%

SET PGPASSWORD=
echo on

#Execute Backup for database
D:\PGBIN\pg_dump -h localhost -p 5432 -U postgres -F c -b -v -f %BACKUP_FILE1% %db1%
D:\PGBIN\pg_dump -h localhost -p 5432 -U postgres -F c -b -v -f %BACKUP_FILE2% %db2%
echo Backup Completed

#Maintain Backup for 4 days
FORFILES /p %BACKUPDIR% /S /D -4 /C "cmd /c IF @isdir == TRUE RMDIR /S /Q @path"
echo Directory older than 4 days are deleted