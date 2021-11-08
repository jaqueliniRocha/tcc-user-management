import json

from cpf_generator import CPF
import requests as requests
import faker

if __name__ == '__main__':
    for x in range(0, 10):
        f = faker.Faker('pt_BR')
        url = "https://tcc-user-management.herokuapp.com/user"
        data = {
            "firstName": f.first_name(),
            "lastName": f.last_name(),
            "email": f.email(),
            "password": "abc",
            "cpf": CPF.generate(),
            "address": {
                "street": f.street_name(),
                "number": 4142,
                "city": f.city(),
                "state": "Rio Grande do Sul",
                "zipcode": f.postcode(),
                "description": "CASA"
            },
            "pets":[
                {
                    "name": f.first_name(),
                    "breed": "vira-lata"
                }
            ],
            "category": "CUSTOMER",
            "profile": "USR"
        }
        print(data)
        headers = {'Content-type': 'application/json'}

        r = requests.post(url, data=json.dumps(data), headers=headers)
        print(r.status_code)
        print(r.text)

    for x in range(0, 3):
        f = faker.Faker('pt_BR')
        url = "https://tcc-user-management.herokuapp.com/user"
        data = {
            "firstName": f.first_name(),
            "lastName": f.last_name(),
            "email": f.email(),
            "password": "abc",
            "cpf": CPF.generate(),
            "address": {
                "street": f.street_name(),
                "number": 4142,
                "city": f.city(),
                "state": "Rio Grande do Sul",
                "zipcode": f.postcode(),
                "description": "CASA"
            },
            "category": "VETERINARY",
            "profile": "ADM"
        }
        print(data)
        headers = {'Content-type': 'application/json'}

        r = requests.post(url, data=json.dumps(data), headers=headers)
        print(r.status_code)
        print(r.text)