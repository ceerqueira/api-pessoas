## API Para Cadastrar Pessoas

Para acessar a documentação Swagger, siga estas etapas:

1. Após iniciar o aplicativo, abra um navegador da web.

2. Acesse a URL do Swagger em `http://localhost:8080/swagger-ui/index.html`.

3. Você verá a interface do Swagger, que lista todos os endpoints disponíveis e permite que você os teste diretamente a partir do navegador.

## Tipos de Requisições:

### Pessoas (/pessoas)

Nesta seção, é possível criar, editar e listar as pessoas.

**POST** CADASTRAR PESSOA `http://localhost:8080/pessoas`

Para realizar uma requisição do tipo Post é necessário usar o formato JSON e informar o nome e a data de nascimento; caso contrário, ele lançará uma exceção.

```json
{
  "nome": "Fulano",
  "dataNascimento": "1996-10-18"
}
```

**GET** LISTAR TODAS AS PESSOAS `http://localhost:8080/pessoas`

Listará em formato JSON todas as pessoas cadastradas.

**GET** BUSCAR POR PESSOA `http://localhost:8080/pessoas/(idPessoa)`

Listará em formato JSON a pessoa e seu endereço principal.


**PUT** ATUALIZAR DADOS DA PESSOA`http://localhost:8080/pessoas`

Para realizar uma alteração é necessário informar o `idPessoa`. Se desejar alterar o endereço principal, é necessário informar o novo `idEnderecoPrincipal`, que deve pertencer à mesma `idPessoa`; caso contrário, ele lançará uma exceção.

```json
{
  "idPessoa": 1,
  "nome": "José",
  "dataNascimento": "1926-10-17",
  "idEnderecoPrincipal": 1
}
```

### Endereço (/endereco)

**POST** CADASTRAR ENDEREÇO DE UMA PESSOA `http://localhost:8080/endereco`

Para realizar uma requisição do tipo Post é necessário usar o formato JSON e informar o `idPessoa`, `logradouro`, `numero` e `cep`; caso contrário, ele lançará uma exceção.

```json
{
  "logradouro": "Rua Marechal",
  "numero": 123,
  "cep": "123123-12",
  "cidade": "Rodeio",
  "idPessoa": 1
}
```

**GET** LISTAR TODOS OS ENDEREÇOS `http://localhost:8080/endereco`

Listará em formato JSON todas os endereços cadastradas.


**GET** BUSCAR ENDEREÇO PELO ID DA PESSOA `http://localhost:8080/endereco/(idPessoa)`

Essa requisição faz uma busca pelo `idPessoa` e retorna os endereços cadastrados da pessoa.
