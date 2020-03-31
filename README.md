# Aplicação CIF - Person API

Microserviço que contempla o gerenciamento dos dados de pessoas da base da aplicação CIF.

# Veja Também
Os links abaixo levam as outras API's usadas no projeto. 
 * [Question API](https://github.com/MathLanca/QuestionAPI/blob/master/README.md)
 * [Information Sorce API](https://github.com/MathLanca/informationSourceAPI/blob/master/README.md)
 * [Evaluation API](https://github.com/MathLanca/evaluationAPI/blob/master/README.md)


# Endpoints e Responses
Base Url: https://java-cif-person-api.herokuapp.com

List All Patients
-
* Lista todos os pacientes ativos na base
```http
 GET /v1/person/listAllPatient
```
Example Response
```json
[
    {
        "id": "string",
        "cpf": "string",
        "firstName": "string",
        "lastName": "string",
        "birthDate": "date time",
        "email": "sintr",
        "password": "string",
        "sex": "string",
        "telephoneNumber": "string",
        "address": {
            "publicPlace": "string",
            "houseNumber": 213,
            "neighborhood": "string",
            "city": "string",
            "state": "string",
            "postalCode": "string"
        },
        "patient": {
            "therapist": {
                "id": "string",
                "cpf": "string",
                "firstName": "string",
                "lastName": "string",
                "birthDate": "date time",
                "email": "string",
                "password": "string",
                "sex": "string",
                "telephoneNumber": "string",
                "address": {
                    "publicPlace": "string",
                    "houseNumber": 213,
                    "neighborhood": "string",
                    "city": "string",
                    "state": "string",
                    "postalCode": "string"
                },
                "active": true
            },
            "note": "string"
        },
        "active": true
    }
]
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |


List All Therapist
-
* Lista todos os terapeutas ativos na base
```http
 GET /v1/person/listAllTherapist
```
Example Response
```json
[
    {
        "id": "string",
        "cpf": "string",
        "firstName": "string",
        "lastName": "string",
        "birthDate": "date time",
        "email": "sintr",
        "password": "string",
        "sex": "string",
        "telephoneNumber": "string",
        "address": {
            "publicPlace": "string",
            "houseNumber": 213,
            "neighborhood": "string",
            "city": "string",
            "state": "string",
            "postalCode": "string"
        },
        "active": true
    }
]
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |


Find By ID
-
* Retorna os dados referente ao ID recebido
```http
 GET /v1/person/findById/{id}
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Person ID  |  **TRUE**    |

Example Response
```json
{
    "id": "string",
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "active": true
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |   Message   |
| :--- | :--- | :---    |
| 200 | `OK` | [RESPONSE] |
| 400 | `BAD REQUEST` | |
| 404 | `NOT FOUND` |   `Could not find person` |
| 500 | `INTERNAL SERVER ERROR` |  `Error while finding person with ID [ID]` |

Find By ID
-
* Retorna os pacientes referente ao ID do terapeuta recebido
```http
 GET /v1/person/findPatientsByTherapist/{id}
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Person ID  |  **TRUE**    |

Example Response
```json
[
    {
        "id": "string",
        "cpf": "string",
        "firstName": "string",
        "lastName": "string",
        "birthDate": "date time",
        "email": "sintr",
        "password": "string",
        "sex": "string",
        "telephoneNumber": "string",
        "address": {
            "publicPlace": "string",
            "houseNumber": 213,
            "neighborhood": "string",
            "city": "string",
            "state": "string",
            "postalCode": "string"
        },
        "active": true
    }
]
```

## Status Codes

Possiveis Retornos

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` | [RESPONSE] |
| 400 | `BAD REQUEST` | 
| 404 | `NOT FOUND` |   
| 500 | `INTERNAL SERVER ERROR` | 

Login
-
* Verifica as informações passadas e se estiverem corretas, retornam as informações dos mesmos
```http
 GET /v1/person/login
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `Header` | `userLogin` | `string` | CPF ou Email  |  **TRUE**    |
| `Header` | `password` | `string` | Senha  |  **TRUE**    |

Example Response
```json
{
    "id": "string",
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "active": true
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |   Message   |
| :--- | :--- | :---    |
| 200 | `OK` | `response` |
| 400 | `BAD REQUEST` | |
| 404 | `NOT FOUND` |   `Could not find person` |
| 401 | `UNAUTHORIZED` |   `Usuario ou senha incorretos` |
| 500 | `INTERNAL SERVER ERROR` |  `Error while validating userLogin and password` |

Delete Person
-
* Inativa o usuario
```http
 DELETE /v1/person/delete/{id}
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Person ID  |  **TRUE**    |

## Status Codes

Possiveis Retornos

| Status Code | Description |   Message   |
| :--- | :--- | :---    |
| 201 | `NO CONTENT` |  |
| 400 | `BAD REQUEST` | |
| 404 | `NOT FOUND` | |
| 500 | `INTERNAL SERVER ERROR` |  `Could not delete person` |

Update Password
-
* Atualiza a senha
```http
 PUT /v1/person/updatePassword/{id}
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Person ID  |  **TRUE**    |
| `header` | `password` | `string` | Nova Senha  |  **TRUE**    |

Example Response
```json
{
    "id": "string",
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "active": true
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |   Message   |
| :--- | :--- | :---    |
| 200 | `OK` |  |
| 400 | `BAD REQUEST` | |
| 404 | `NOT FOUND` | |
| 500 | `INTERNAL SERVER ERROR` |  `Could not delete person` |

Update Password
-
* Ativa o cadastro da pessoa
```http
 PUT /v1/person/reactivatePerson/{id}
```
|   Where   | Parameter | Type | Description |  Required    |
| :--- | :--- | :--- | :--- | :--- |
| `path` | `id` | `string` | Person ID  |  **TRUE**    |

Example Response
```json
{
    "id": "string",
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "active": true
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |   Message   |
| :--- | :--- | :---    |
| 200 | `OK` |  |
| 400 | `BAD REQUEST` | |
| 404 | `NOT FOUND` | |
| 500 | `INTERNAL SERVER ERROR` |  `Could not delete person` |

Register Therapist
-
* Cadastra um novo terapeuta

```http
 POST /v1/person/register
```
Body
```json
{
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "active": true
}
```

Example Response
```json
{
    "id": "string",
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "active": true
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |   Message   |
| :--- | :--- | :---    |
| 201 | `CREATED` |  |
| 400 | `BAD REQUEST` | |
| 403 | `FORBIDDEN` | `Please enter a valid CPF` |
| 404 | `NOT FOUND` | |
| 500 | `INTERNAL SERVER ERROR` |  `Could not register the person` |


Register Patient
-
* Cadastra um novo paciente

```http
 POST /v1/person/register
```
Body
```json
{
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "patient": {
    	"therapistID": "string",
    	"note":"string"
    },
    "active": true
}
```

Example Response
```json
{
    "id": "string",
    "cpf": "string",
    "firstName": "string",
    "lastName": "string",
    "birthDate": "date time",
    "email": "sintr",
    "password": "string",
    "sex": "string",
    "telephoneNumber": "string",
    "address": {
        "publicPlace": "string",
        "houseNumber": 213,
        "neighborhood": "string",
        "city": "string",
        "state": "string",
        "postalCode": "string"
    },
    "patient": {
      "therapist": {
        "id": "string",
        "cpf": "string",
        "firstName": "string",
        "lastName": "string",
        "birthDate": "date time",
        "email": "sintr",
        "password": "string",
        "sex": "string",
        "telephoneNumber": "string",
        "address": {
            "publicPlace": "string",
            "houseNumber": 213,
            "neighborhood": "string",
            "city": "string",
            "state": "string",
            "postalCode": "string"
        },
        "active": true
      },
      "note": "string"
    },
    "active": true
}
```

## Status Codes

Possiveis Retornos

| Status Code | Description |   Message   |
| :--- | :--- | :---    |
| 201 | `CREATED` |  |
| 400 | `BAD REQUEST` | |
| 403 | `FORBIDDEN` | `Please enter a valid CPF` |
| 404 | `NOT FOUND` | |
| 500 | `INTERNAL SERVER ERROR` |  `Could not register the person` |