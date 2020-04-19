import database_provider
from app import app

if __name__ == "__main__":
    app.run()


@app.route('/test')
def test():
    connection = database_provider.get_database_connection()
    cursor = connection.cursor()
    cursor.execute('select * from test')