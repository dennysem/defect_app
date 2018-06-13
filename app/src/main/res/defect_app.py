import psycopg2
import json
from flask import Flask, json, request, make_response, jsonify
import logging
import traceback

app = Flask(__name__)

POSTGRES_URL = "0.0.0.0"
POSTGRES_USER = "postgres"
POSTGRES_NAME = "postgres"
POSTGRES_PASSWORD = "$password"
connection = psycopg2.connect(user=POSTGRES_USER,
                              host=POSTGRES_URL,
                              password=POSTGRES_PASSWORD, database = 'postgres', port = '5432')


def qt(str):
    return """'""" + str + """'"""

def strip_quotes(s):
    if len(s) < 2:
        return s
    if s[0] in ['"', "'"]:
        s = s[1:]
    if s[-1] in ['"', "'"]:
        s = s[:-1]
    return s

def write_to_postgres(defect):
    cur = connection.cursor()
    try:
        query = "INSERT INTO DEFECT (CITY, STREET, NUMBER, SENDER) " + "VALUES (" + qt(defect['city']) + ", " \
                + qt(defect['street']) + ", " + qt(defect['number']) + ", " + qt(defect['sender']) + ");"
        cur.execute(query)
        connection.commit()
    except:
        raise
    finally:
        cur.close()


@app.route('/write_defect', methods=['POST'])
def write_defect():
    try:
        logging.warning(json.dumps(request.args))
        write_to_postgres(request.args)
        return make_response(jsonify("1"), 200)
    except:
        logging.warning("Error in parsing query: " + json.dumps(request.json))
        logging.warning(traceback.print_exc())
        return make_response(jsonify("0"), 500)


def all_defects():
    cur = connection.cursor()
    try:
        query = "select (city, street, number, sender, timestamp) from defect order by timestamp desc;"
        cur.execute(query)
        result = []
        for response in cur.fetchall():
            sql_response = response[0][1:-1].split(',')
            defect = {}
            defect['city'] = strip_quotes(sql_response[0])
            defect['address'] = strip_quotes(sql_response[2]) + ' ' + strip_quotes(sql_response[1])
            defect['address'] = defect['address'].strip()
            defect['sender'] = sql_response[3][1:-1]
            defect['created_at'] = sql_response[4].split()[0][1:]
            result.append(defect)
        return result
    except:
        raise
    finally:
        cur.close()


@app.route('/get_defects', methods=['GET'])
def get_defects():
    try:
        response = all_defects()
        return make_response(jsonify(response), 200)
    except:
        logging.warning("Error in parsing query: " + json.dumps(request.json))
[ec2-user@ip-172-31-22-95 ~]$
        logging.warning(traceback.print_exc())
        return make_response(jsonify("0"), 500)
[ec2-user@ip-172-31-22-95 ~]$ sudo python defect_service.py



@app.route('/')
def hello_world():
    return 'Hello, World!'


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=80)
    