
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