from flask import Flask, render_template, request, redirect, url_for, flash
from flask_mysqldb import MySQL


app = Flask(__name__)
app.secret_key = 'secret_key'
app.config['MYSQL_HOST'] = $<mysql_host>
app.config['MYSQL_USER'] = $<mysql_user>
app.config['MYSQL_PASSWORD'] = $<mysql_password>
app.config['MYSQL_DB'] = $<mysql_db>
mysql = MySQL(app)


@app.route('/')
def Index():
    cur = mysql.connection.cursor()
    cur.execute("SELECT  * FROM $<tableName>")
    data = cur.fetchall()
    cur.close()
    return render_template('index.html', fetchedRows=data)

@app.route('/insert', methods = ['POST'])
def insert():
    if request.method == "POST":
        flash("Data Inserted Successfully")
        $<formFieldsPython>
        cur = mysql.connection.cursor()
        $<insertStatement>
        mysql.connection.commit()
        return redirect(url_for('Index'))


@app.route('/delete/<string:id_data>', methods = ['GET'])
def delete(id_data):
    flash("Record Has Been Deleted Successfully")
    cur = mysql.connection.cursor()
    $<deleteStatement>
    mysql.connection.commit()
    return redirect(url_for('Index'))


@app.route('/update',methods=['POST','GET'])
def update():
    if request.method == 'POST':
        $<updateFormFields>
        cur = mysql.connection.cursor()
        $<updateStatement>
        flash("Data Updated Successfully")
        mysql.connection.commit()
        return redirect(url_for('Index'))


if __name__ == "__main__":
    app.run(debug=True)
