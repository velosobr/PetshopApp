# Petshop App
Android App using [Petshop mock.IO API](https://run.mocky.io/v3/039423ea-9e2b-423e-829e-7b5c789a9703)


De forma geral tentei aplicar os conceitos de arquitetura limpa, separando em pastas indicados no modelo clean archtecture (data, presentation, framework, usecase).
Faltou apenas terminar a parte de detalhes do item, que não deu tempo, porém já está quase pronta.

Demonstrei conhecimentos de testes unitários também, testando, repository, usecases, e viewmodels.

De forma geral tentei aplicar conceitos de grandes apps, mas tudo de forma minimizada, tendo em vista ser uma api simples, com poucas features.

Optei por utilizar Hilt pois para projetos assim rapidos me sinto mais a vontade, mas não teria problema em fazer com Koin.


# Description
GOAL: criar um aplicativo que irá consumir uma REST API que simule as operações básicas de um PetShop Virtual.

Detalhes: a operação do App deve permitir a visualização dos itens. Cada item conterá detalhes como o valor, descrição, tamanho e peso que devem ser exibidos ao selecioná-la. O usuário poderá adicionar os itens ao carrinho e ao final da simulação de compra, esta lista poderá ser compartilhada por e-mail e/ou whats.

## Libraries Used
Coroutine, LiveData, Retrofit2, Coil, Dagger-Hilt, mockk & JUnit.


## Features Done
-  visualização dos itens.
- Adicionar ao carrinho.
- compartilhar informação via sms/whatsapp
## Features in progress
    - detalhes dos itens.

## improvements todo
kapt to ksp
separate into core and app modules
create a cache for offline first with a new datasource layer

## improvements done
   
## TO DO
     - verificar se precisar limpar carrinho
