# Getting Started


## Ambiente & Instalação

Pré-requisito para execução da aplicação:
* Java JDK 1.8+


## Inicialização da aplicação

Para executar a suite de testes da aplicação um dos caminhos é executar o _goal_ _test_ do maven conforme comando que se segue:
```
./mvnw test
```

Para executar a aplicação, executar o _goal_ _spring-boot:run_ do maven conforme comando que se segue:

```
./mvnw spring-boot:run
```

Uma vez que visualize no console a informação que a apliacação foi inicializada com sucesso, comnforme exemplo do trecho de log abaixo, você está apto a executar os testes:

```
2020-06-22 07:54:43.280  INFO 9992 --- [           main] b.c.i.i.challenge.ChallengeApplication   : Started ChallengeApplication in 1.294 seconds (JVM running for 1.556)
```

## Execução de teste

Uma vez que tenha realizado a inicialização a aplicação estará apta para receber as requições de teste. 


### Informações do serviço
POST /validate

**Request**

```
{
  "value": "Bgtyhn56!"
}
```

**Responses**

__204 - No Content__, senha aderente aos critérios de validação de segurança.

__400 - Bad Request__, Erro de validação de campos ou senha não aderente a validação de segurança.

```
{
  "messages":[
     "Password does not match the strength criteria."
  ]
}
```


### Caso de teste: Execução com sucesso de una senha aderente aos critérios de seguraça

```
curl -v --request POST \
  --url http://localhost:8080/validate \
  --header 'accept: application/json' \
  --header 'content-type: application/json' \
  --data '{"value": "Bgtyhn56!"}'
```

Exemplo de saída
```
> POST /validate HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> accept: application/json
> content-type: application/json
> Content-Length: 22
> 
* upload completely sent off: 22 out of 22 bytes
< HTTP/1.1 204 
< Content-Length: 0
< Date: Mon, 22 Jun 2020 10:47:47 GMT
< 
```

### Caso de teste: Execução com erro de validação critérios de segurança da senha

```
curl -v --request POST \
  --url http://localhost:8080/validate \
  --header 'accept: application/json' \
  --header 'content-type: application/json' \
  --data '{"value": "gtyhn6!"}'
```

Exemplo de saída
```
> POST /validate HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> accept: application/json
> content-type: application/json
> Content-Length: 20
> 
* upload completely sent off: 20 out of 20 bytes
< HTTP/1.1 400 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 22 Jun 2020 10:48:53 GMT
< Connection: close
< 
* Closing connection 0
{"messages":["Password does not match the strength criteria."]}%         
```

### Caso de teste: Execução com erro de validação campos obrigatórios


```
curl -v --request POST \
  --url http://localhost:8080/validate \
  --header 'accept: application/json' \
  --header 'content-type: application/json' \
  --data '{"value": ""}'
```

Exemplo de saída
```
> POST /validate HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> accept: application/json
> content-type: application/json
> Content-Length: 13
> 
* upload completely sent off: 13 out of 13 bytes
< HTTP/1.1 400 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 22 Jun 2020 11:06:57 GMT
< Connection: close
< 
* Closing connection 0
{"messages":["Validation error on field 'value': must not be empty"]}%          
```