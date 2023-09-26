-- Crie a tabela pessoas sem a chave estrangeira para endereco
CREATE TABLE pessoas
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    endereco_principal_id INT
);

CREATE TABLE endereco
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    logradouro VARCHAR(255),
    cep VARCHAR(10),
    numero INT,
    cidade VARCHAR(255),
    pessoa_id INT
);

ALTER TABLE pessoas
ADD CONSTRAINT FK_pessoa_endereco FOREIGN KEY (endereco_principal_id) REFERENCES endereco(id);

ALTER TABLE endereco
ADD CONSTRAINT FK_endereco_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoas(id);
