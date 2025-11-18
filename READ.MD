# 🛒 MercadoLang — Mini Compilador em Java

## 👤 Autores
- **Pedro Henrique de Sá Gomes** — RA: **12524232477**
- **Felipe de Ornelas Chaves** — RA: **12525178266**

Este projeto implementa um **compilador completo**, desenvolvido em Java, para uma linguagem simples inspirada em operações de um mercado.  
A linguagem permite adicionar itens, vender, atualizar, listar, mostrar estoque, mostrar faturamento e gerar um relatório final.

O objetivo é demonstrar, na prática, todas as fases fundamentais de um compilador:

- **Análise Léxica (Lexer)**
- **Análise Sintática (Parser)**
- **AST (Árvore Sintática Abstrata)**
- **Análise Semântica**
- **Tabela de Símbolos**
- **Execução**
- **Relatório Final**

---

# 📂 Estrutura do Projeto

/src
├── Lexer.java
├── Parser.java
├── Ast.java
├── SymbolTable.java
├── Semantic.java
├── Main.java
└── mercado.txt (arquivo de entrada)



O arquivo `mercado.txt` contém o código-fonte da linguagem MercadoLang que será compilado e executado.

---

# 🚀 Como Executar

### ✔ Requisitos:
- Java 8+
- Qualquer IDE (NetBeans, IntelliJ, Eclipse) ou terminal

### ✔ Passos:
1. Coloque seu arquivo de entrada na pasta:
2. Execute a classe **Main.java**
3. O compilador irá:
    - Ler o arquivo
    - Gerar tokens
    - Criar a AST
    - Executar os comandos
    - Exibir resultados + relatório final

---

# 🛠 Linguagem MercadoLang

Abaixo estão todos os comandos suportados pela linguagem.

## 📌 ADICIONAR ITEM
## 📌 VENDER ITEM
## 📌 ATUALIZAR PREÇO
## 📌 REMOVER ITEM
## 📌 LISTAR ITENS
## 📌 MOSTRAR ESTOQUE
## 📌 MOSTRAR FATURAMENTO

---

# 📘 Exemplo Completo de Arquivo `mercado.txt`

```txt
ADICIONAR "Banana" PRECO 2.50 ESTOQUE 20
ATUALIZAR PRECO "Banana" VALOR 3.00
LISTAR ITENS
VENDER "Banana" QUANTIDADE 5
REMOVER "Banana"
MOSTRAR ESTOQUE
MOSTRAR FATURAMENTO




