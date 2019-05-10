

# SGC - Sistema Gerenciador de Concessionária
#### [Projeto prático](https://github.com/deyvidandrades/SGC) desenvoldido na disciplina de Paradigmas de Linguagens de Programação.

A proposta inicial é de apresentar um sistema de gestão para concessionárias, onde os gerentes e diretores possam acompanhar o estoque de automóveis, solicitar novos veículos e acompanhar as vendas. Onde os vendedores possam consultar os dados à cerca do veículo e realizar a venda de um veículo à um cliente. O foco principal tornar simples os processos de compra, venda e reposição de automóveis para diferentes concessionárias.

## Primeiros passos

Essas instruções farão com que você tenha uma cópia do projeto em execução na sua máquina local para fins de desenvolvimento e teste. Veja a implantação de notas sobre como implantar o projeto em um sistema ativo.

### Prérequisitos

O que você precisa instalar para rodar o software:
* [Java Jdk](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) - Ambiente de desenvolvimento Java
* [Intellij Idea](https://www.jetbrains.com/idea/download/) - IDE usada no projeto




## Baixe ou clone o repositório
```
> git clone https://github.com/deyvidandrades/SGC.git
```

## Configurando a IDE Intellij Idea

Passo a passo que informam como configurar o ambiente de desenvolvimento para execução.

Importe as bibliotecas utilizadas no projeto, localizadas em: (**src/bibliotecas**)

```
file > Project Estructure > Libraries > + > Java > (biblioteca) > OK
```

## Configurando os artefatos
Configure os artefatos e marque a opção **include in project build**

```
file > Project Estructure > Artifacts > + > JAR > from modules with dependencies >
```
## Construindo o projeto
Construa o projeto (ctrl+f9)
Crie uma configuração de executável **Jar Application**:
```
> Run > Edit Configurations > + > Jar Application
```
Selecione o caminho onde foi criado o .jar: (**/output/artifacts/dal_jar/DAL.jar**)

## Executando  SGC
Adicione o artefato à nova configuração:
```
> Before Launch > + > Build Artifacts > DAL.jar
```

Agora você pode rodar o programa diretamente do Intellij Idea.


## Como usar o SGC

### Login
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
