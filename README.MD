 
 Empacotar e testes unitários:
 
    mvn clean package
 
 Subir a aplicação:
    
    mvn exec:java
 
 
 Testes de Integração (**após subir a aplicação**):
 
    mvn integration-test
 
 
 Pelo arquivo **src/main/resources/imdb-client.properties** é possível configurar a porta TCP e número de Threads.
 
 A porta default é 8181 e o número de threads é igual o número de núcleos da CPU.
 
 Exemplo de request (GET):
        
        http://localhost:8181/search/movie?title=batman 
        

Para mais detalhes sobre as requisições veja a classe **MovieSearchTest.java**

ansible
    
    ansible-playbook create-imdb-image.yml
Rafael        