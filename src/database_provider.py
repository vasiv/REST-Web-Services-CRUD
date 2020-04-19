import getopt
import sys
import cx_Oracle
import config


def describe(connection, tablename):
    cur = connection.cursor()
    cur.execute('select * from %s where 1=0' % tablename)
    for desc in cur.description:
        column_name = desc[0]
        nullable = desc[6]
        data_type = desc[1].__name__
        data_length = desc[3]
        print(column_name, nullable, data_type, data_length)


def get_database_connection():
    return cx_Oracle.connect(config.db_user + '/' + config.db_password + '@' + config.db_address + ':' +
                                   config.db_port + '/' + config.db_name)


connection = get_database_connection()
cursor = connection.cursor()
describe(connection, 'tablename')
cursor.close()
connection.close()