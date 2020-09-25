### Desarrollo de ejercicio con la siguiente especificación:

#### Generar un proyecto con las siguientes características

A. El proyecto debe exponer los siguientes servicios REST (Persistencia de
datos se debe realizar utilizando un esquema local en MySQL)

    a. Servicio de creación de usuarios que deberá recibir
    
        i. Name
        
        ii. Username
        
        iii. Email
        
        iv. Phone

    b. Listado de usuarios registrados
    c. Obtención de usuario registrado mediante email
    
B. Generar un servicio POST que consultará a una API externa y que deberá
considerar la recepción de un parámetro (param)

    a. Servicio deberá cifrar el parámetro utilizando DES con la llave
    “ionix123456”
    b. Se deberá invocar la siguiente URL entregando el parámetro cifrado
    como valor
        i. GET https://sandbox.ionix.cl/test-tecnico/search?rut=
    c. El valor parámetro de prueba es “1-9”
    
C. Servicio debe retornar un objeto JSON en el formato indicado con la
cantidad de registros devuelto por el servicio llamado anteriormente. En el
campo “elapsedTime” se debe entregar el tiempo de ejecución del servicio
“search” en milisegundos

```json
{
    "responseCode": 0
    "description": "OK",
    "elapsedTime": 245,
    "result": {
      "registerCount": NN
    }
}
```

#### Ejecució pruebas unitarias:

`mvn clean test`

#### Generación reporte de cobertura JaCoCo
 `mvn jacoco:report`
 
#### Levantar aplicación (por defecto puerto 8080)
 `mvn spring-boot:run`
 
#### Llamadas de prueba
- Servicio que crea usuario

`curl --location --request POST 'http://localhost:8080/sistema/usuario' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" :"nnn",
    "username" : "username",
    "email" : "email",
    "number" : 123
}'`
- Servicio que consulta todos los usuarios

`curl --location --request GET 'http://localhost:8080/sistema/usuario'`

- Servicio que busca usuario por email

`curl --location --request GET 'http://localhost:8080/sistema/usuario/email/email@ccc.com'`

- Servicio que llama a Sandbox

`curl --location --request POST 'http://localhost:8080/sandbox/usuario?param=1-9'`


##### Autor: Fabián Arias