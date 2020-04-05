import getopt
import sys
import cx_Oracle

def describe(connection, tablename):
	cur = connection.cursor()
	cur.execute('select * from %s where 1=0' % tablename)
	for desc in cur.description:
		column_name = desc[0]
		nullable = desc[6]
		data_type = desc[1].__name__
		data_length = desc[3]
		print (column_name, nullable, data_type, data_length)

connection = cx_Oracle.connect('user/password@localhost:1521/database')
cursor = connection.cursor()
describe(connection, 'tablename')
cursor.close()
connection.close()