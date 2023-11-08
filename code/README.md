Detalhamento do código:

- Classe KeyValueDB:

Esta classe representa o banco de dados de valores-chave. Possui os seguintes métodos:

KeyValueDB(String fileName)
> Construtor que inicializa o banco de dados com o nome de arquivo especificado.

insert(int key, String value)
> Insere um novo par chave-valor no banco de dados.

remove (int key)
> Remove o par chave-valor com a chave especificada do banco de dados.

search(int key)
> Pesquisa o valor associado à chave especificada e o retorna se encontrado.

update(int key, String newValue)
> Atualiza o valor associado à chave especificada com o novo valor.

loadFromFile()
> Carrega os dados do banco de dados do arquivo especificado.

saveToFile()
> Salva os dados do banco de dados no arquivo especificado.

- Função principal:

A função main() é o ponto de entrada do programa. Ela analisa os argumentos da linha de comando e executa a operação apropriada do banco de dados. Resumo dos comandos:

inserir
> Insere um novo par chave-valor.

removedor
> Remove um par de valores-chave.

buscar
> Pesquisa o valor associado a uma chave.

atualizar
> Atualiza o valor associado a uma chave.

O código valida os argumentos de entrada e trata possíveis erros, como acesso inválido a arquivos ou formato de chave inválido.
