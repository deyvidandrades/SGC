
# SGC - Sistema Gerenciador de Concessionária

A proposta inicial é de apresentar um sistema de gestão para concessionárias, onde os gerentes e diretores possam acompanhar o estoque de automóveis, solicitar novos veículos e acompanhar as vendas. Onde os vendedores possam consultar os dados à cerca do veículo e realizar a venda de um veículo à um cliente. O foco principal tornar simples os processos de compra, venda e reposição de automóveis para diferentes concessionárias.

## Primeiros passos

Essas instruções farão com que você tenha uma cópia do projeto em execução na sua máquina local para fins de desenvolvimento e teste. Veja a implantação de notas sobre como implantar o projeto em um sistema ativo.

### Prérequisitos

O que você precisa instalar para rodar o software:
* [Java Jdk](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) - Ambiente de desenvolvimento Java
* [Intellij Idea](https://www.jetbrains.com/idea/download/) - IDE usada no projeto
* [Commons-io](https://github.com/deyvidandrades/SGC/tree/master2/src/bibliotecas/commons-io-2.6.jar) - Commons-io 2.6
* [JSON](https://github.com/deyvidandrades/SGC/tree/master2/src/bibliotecas/json-20180813.jar) - Json-java 20180813
* [ObjectMapper](https://github.com/deyvidandrades/SGC/tree/master2/src/bibliotecas/jackson-all-1.9.0.jar) - Jackson




### Baixe ou clone o repositório
```
> git clone https://github.com/deyvidandrades/SGC.git
```

### Configurando a IDE Intellij Idea

Passo a passo que informam como configurar o ambiente de desenvolvimento para execução.


Abra o projeto no Intellij Idea para configurar os artefatos e o executável

```
file > Project Estructure > Artifacts > + > JAR > from modules with dependencies >
```
Construa o projeto e antes de compilar e executar faça o seguinte:
```
Run > Edit Configurations > + > Jar Application

Selecione o pach onde foi criado o .jar:
/output/artifacts/dal_jar/DAL.jar
```
Adicione o artefato à nova configuração:
```
> Before Launch > + > Build Artifacts > DAL.jar
```

Agora você pode rodar o programa diretamente do Intellij Idea.

## Rodando o SGC

Como usar o SGC

### Usuários

Entre com o login e a senha padrão e crie seu usuário e dê as devidas permissões.

```
admin admin
```
Simule a venda de carros, controle de estoque e financeiro

## Ferramentas e Bibliotecas

* [Java Jdk](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) - Ambiente de desenvolvimento Java
* [Intellij Idea](https://www.jetbrains.com/idea/download/) - IDE usada no projeto
* [Commons-io](https://github.com/deyvidandrades/SGC/tree/master2/src/bibliotecas/commons-io-2.6.jar) - Commons-io 2.6
* [JSON](https://github.com/deyvidandrades/SGC/tree/master2/src/bibliotecas/json-20180813.jar) - Json-java 20180813
* [ObjectMapper](https://github.com/deyvidandrades/SGC/tree/master2/src/bibliotecas/jackson-all-1.9.0.jar) - Jackson

## Versão

2.0.1

## Autores

* **Deyvid Andrade** - [Instagram](https://instagram.com/deyvidandrades)
* **Arthur Hauck Dittz** - [Instagram](https://instagram.com/arthurhd)
* **Luiz Carlos Nascimento** - [Instagram](https://instagram.com/luizcnfilho)
* **Vinícius Resende** - [Instagram](https://instagram.com/vinny_u.u_)

## Licença
Este projeto está licenciado sob a licença MIT - consulte o arquivo [LICENSE.md](LICENSE.md) para obter detalhes
