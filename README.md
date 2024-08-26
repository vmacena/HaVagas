# HaVagas

HaVagas é um projeto Android desenvolvido para permitir que pessoas se cadastrem em uma plataforma de empregos. O projeto contém uma única tela com campos que compõem um cadastro.

## Funcionalidades

A tela de cadastro contém os seguintes campos:

- **Nome completo**
- **E-mail** e uma opção para que o usuário indique se deseja ou não receber e-mails com atualizações de oportunidades.
- **Telefone** (o usuário poderá indicar se o telefone é comercial ou residencial).
- **Telefone celular** (este campo só será exibido caso o usuário selecione uma opção para adicionar o telefone celular).
- **Sexo**
- **Data de nascimento**
- **Formação** (fundamental, médio, graduação, especialização, mestrado e doutorado). Para cada formação, os seguintes campos adicionais devem ser exibidos:
  - Fundamental e médio: ano de formatura.
  - Graduação e especialização: ano de conclusão e instituição.
  - Mestrado e doutorado: ano de conclusão, instituição, título de monografia e orientador.
- **Vagas de interesse** (campo de texto aberto).
- **Botões para salvar e limpar o formulário**.

## Comportamento dos Botões

- **Salvar**: Mostra todos os campos que foram preenchidos em uma mensagem pop-up para o usuário.
- **Limpar**: Limpa todos os campos do formulário.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Android SDK**: Ferramentas e APIs para desenvolvimento Android.
