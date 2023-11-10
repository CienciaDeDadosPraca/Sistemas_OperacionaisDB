
# COMANDO UTEIS GIT

### COMANDO UTEIS GIT

    1. CRIAÇÃO DE TAG COM MARCAÇÃO DE VERSÃO :
        git tag -a "versao(pexemplo v1.0) -m "mensagem na criação da tag"

    2. SE DESEJAR SABER QUAL URL DO REPOSITORIO REMOTO ESTÁ ASSOCIADO AO REPOSITORIO LOCAL:
        git remote -v
    3. CRIAÇÃO DE BRANCH E ASSOCIALA À UMA BRANCH NO REPOSITORIO REMOTO
        git checkout "nome da branch"

/*O CONTEUDO DE SOCKET ESTÁ HOSPEDADO NO link
 * https://www.youtube.com/watch?v=MtAfYUW7fJ4&t=983s
 * MANOEL CAMPOS DEV 1 Criando aplicação CLIENTE/SERVIDOR de CHAT em JAVA usando SOCKETS e THREADS
 */


/*PARA TERMINAR A APLICAÇÃO SERVIDOR DEVE SER INTERROMPIDO O PROCESSO E NÃO APENAS FECHALO 
 * CASO CONTRÁRIO O SERVIDOR CONTINUARÁ CONECTADO E A PORTA SENDO DISPONIBILIZADA PARA AQUELE SERVIDO
 * DEVE-SE FECHAR A IDE E INICIAR DENOVO A EXECUÇÃO DOS PROGRAMAS 
 */
// Duas aplicações no mesmo projeto


    /*A constante PORT é a porta no Sistema operacional onde será iniciada a conexão 
     * Deve ser uma constante publica para que a classe chatClient interaja com esta
     * A constante é estática devido à sua imutabilidade
    */
# BANCO DE DADOS CHAVE-VALOR

A gestão de dados é uma área crucial para a pesquisa e o desenvolvimento de projetos. Uma abordagem cada vez mais relevante nesse contexto é o uso de bancos de dados de chave-valor, que oferecem uma estrutura flexível e escalável para armazenar e manipular informações. Nessa configuração, os dados são organizados em registros contendo duas partes essenciais: uma chave exclusiva que identifica cada registro e um valor que armazena os dados propriamente ditos. Essa estrutura proporciona uma liberdade excepcional para a gestão de dados, permitindo que programas acadêmicos gerenciem informações armazenadas de maneira eficaz e versátil. Esses bancos de dados oferecem uma gama de operações fundamentais para a pesquisa e o desenvolvimento acadêmico, incluindo a capacidade de inserir novos objetos, remover registros, buscar informações específicas e atualizar dados armazenados.

Neste projeto acadêmico, propomos a exploração e implementação de um banco de dados de chave-valor, juntamente com um programa cliente que facilite a interação dos pesquisadores com o banco de dados. O banco de dados a ser desenvolvido atenderá a requisitos funcionais abrangentes, que englobam desde a inserção, remoção e atualização de objetos até a pesquisa de informações e persistência dos dados em um arquivo. Paralelamente, o programa cliente oferecerá comandos correspondentes para interagir com o banco de dados.

Além dos requisitos funcionais, abordaremos também os requisitos não funcionais que são essenciais para o desempenho e a usabilidade do sistema. Isso inclui uma interface de linha de comandos que permite operações diretas, comunicação bidirecional entre o programa cliente e o banco de dados, suporte ao processamento concorrente de solicitações e estratégias de gerenciamento de memória para otimizar o desempenho.

## Integrantes

* Bruno Petrocchi de Sena Azevedo
* Carlos Dias Maia
* Ranier Pereira Nunes de Melo
* Rodrigo de Oliveira Santos
* Samuel Fernandes Teixeira Lages

## Professor

* Prof. Pedro Henrique Penna

## Instruções de utilização

O projeto é uma implementação em Java que se destina a fornecer uma estrutura de banco de dados de chave-valor simples, capaz de realizar várias operações importantes. Atualmente, a versão em desenvolvimento demonstra as seguintes funcionalidades:

1. Criação de Arquivo TXT:
    * O sistema é capaz de criar um arquivo de texto (TXT) no sistema de arquivos, se esse arquivo ainda não existir. Isso é especialmente útil para iniciar um banco de dados vazio. A criação do arquivo é um passo fundamental na configuração inicial do banco de dados.

2. Armazenamento de Objetos no Arquivo TXT:
    * Uma das funcionalidades centrais do projeto é a capacidade de armazenar objetos em um arquivo TXT previamente criado. Esses objetos são mantidos em um HashMap, um tipo de estrutura de dados que permite o armazenamento eficiente de pares chave-valor.

3. Execução de Comandos:
    * O sistema é projetado para processar comandos específicos que são incorporados no código-fonte. Atualmente, ele suporta três comandos essenciais: "inserir", "remover" e "buscar". Esses comandos permitem ao usuário interagir com o banco de dados, inserindo novos pares chave-valor, removendo entradas existentes ou buscando informações específicas com base em uma chave.

## Histórico de versões

* 0.0.4
    * Upload da primeira versão do banco de dados.
* 0.0.3
    * Compilação da primeira versão do banco de dados.
* 0.0.2
    * Elaboração do documento de contexto.
* 0.0.1
    * Definição da linguagem de programação.

