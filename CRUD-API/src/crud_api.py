from app import app

if __name__ == "__main__":
    app.run()


@app.route('/${tableName}', methods=["POST"])
def create():
# create object


@app.route('/${tableName}', methods=["GET"])
def get_all():
# return all objects


@app.route('/${tableName}/<id>', methods=["GET"])
def get_by_id():
# return object by id


@app.route('/${tableName}/<id>', methods=["PUT"])
def update():
# update object



@app.route('/${tableName}/<id>', methods=["DELETE"])
def delete():
# delete object



