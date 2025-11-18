# 🛒 MercadoLang — Compilador Completo em Java  
### 📘 *Projeto A3 – Construção de Compiladores*

---

## 👤 Autores  
- **Pedro Henrique de Sá Gomes** — RA: **12524232477**  
- **Felipe de Ornelas Chaves** — RA: **12525178266**

---

# 📌 Sobre o Projeto

O **MercadoLang** é uma linguagem de programação criada para fins acadêmicos, projetada para simular operações de mercado como cadastro de produtos, vendas, atualização de preços e relatórios.

Este projeto implementa **um compilador real**, cobrindo todas as etapas fundamentais:

✔ Análise Léxica  
✔ Análise Sintática  
✔ Construção da AST  
✔ Análise Semântica  
✔ Tabela de Símbolos  
✔ Execução  
✔ Relatório Final  
✔ Extensões de Linguagem  

O objetivo é demonstrar, na prática, como funciona o fluxo completo de um compilador profissional — mas com uma linguagem simples e intuitiva.

---

# 📑 Sumário

1. [Objetivo do Projeto](#-objetivo-do-projeto)  
2. [Arquitetura do Compilador](#-arquitetura-do-compilador)  
3. [Como Executar](#-como-executar)  
4. [Sintaxe da Linguagem](#-sintaxe-da-linguagem)  
5. [Exemplo Completo de Entrada](#-exemplo-completo-de-entrada)  
6. [Fluxo do Compilador](#-fluxo-do-compilador)  
7. [Estrutura de Arquivos](#-estrutura-de-arquivos)  
8. [Glossário para Prova](#-glossário-para-prova)  
9. [Possíveis Extensões Futuras](#-possíveis-extensões-futuras)  
10. [Licença](#-licença)  

---

# 🎯 Objetivo do Projeto

Criar um compilador funcional, capaz de:

- Interpretar uma linguagem própria (MercadoLang)  
- Entender comandos textuais  
- Validar significados (semântica)  
- Executar ações reais (venda, preço, estoque)  
- Gerar relatórios  
- Auxiliar no entendimento das fases de um compilador real  

Este projeto demonstra **a pipeline completa de compilação**, servindo tanto para fins de aprendizado quanto como base para linguagens mais avançadas.

---

# 🧠 Arquitetura do Compilador

O MercadoLang possui **6 módulos principais**:

### 1️⃣ **Lexer (Análise Léxica)**  
Converte o arquivo `.txt` em tokens como:  
- `ADICIONAR`  
- `STRING`  
- `NUMBER`  
- `PRECO`  

### 2️⃣ **Parser (Análise Sintática)**  
Valida a ordem dos tokens e gera a AST.

### 3️⃣ **AST (Árvore Sintática Abstrata)**  
Classes que representam os comandos da linguagem.

### 4️⃣ **Semantic (Análise Semântica)**  
Valida regras como:  
- Preço > 0  
- Item existir antes de vender  
- Quantidade válida  

### 5️⃣ **SymbolTable (Tabela de Símbolos)**  
É a “memória” da linguagem, onde ficam itens, preços, estoque.

### 6️⃣ **Executor / Runtime**  
Executa efetivamente as operações.

---

# 💻 Como Executar

### ✔ Pré-requisitos
- Java 8+  
- IntelliJ, NetBeans ou terminal  

### ✔ Passo a passo

1. Coloque seu código da linguagem em: src/mercado.txt
2. Execute: 
3. O compilador irá:
- Ler o arquivo  
- Gerar tokens  
- Criar AST  
- Validar semântica  
- Executar operações  
- Imprimir relatório final  

---

# 🛠 Sintaxe da Linguagem (MercadoLang)

## ➕ ADICIONAR ITEM
## 🛒 VENDER ITEM
## ❌ REMOVER ITEM
## 📋 LISTAR ITENS
## 📦 MOSTRAR ESTOQUE
## 💰 MOSTRAR FATURAMENTO



---

# 📘 Exemplo Completo de Entrada

```txt
ADICIONAR "Banana" PRECO 2.50 ESTOQUE 20
ATUALIZAR PRECO "Banana" VALOR 3.00
LISTAR ITENS
VENDER "Banana" QUANTIDADE 5
REMOVER "Banana"
MOSTRAR ESTOQUE
MOSTRAR FATURAMENTO


+--------------+
|   Arquivo    |
| mercado.txt  |
+------^-------+
       |
       |
+------|-------+
|     Lexer    |  → Converte caracteres em TOKENS
+------^-------+
       |
+------|-------+
|     Parser   |  → Monta a AST validando a gramática
+------^-------+
       |
+------|-------+
|      AST     |  → Representação do programa
+------^-------+
       |
+------|-------+
|    Semantic  |  → Valida e executa ações reais
+------^-------+
       |
+------|-------+
|  Runtime/Out | → Resultados + relatório final
+--------------+


/src
 ├── Lexer.java
 ├── Parser.java
 ├── Ast.java
 ├── SymbolTable.java
 ├── Semantic.java
 ├── Main.java
 └── mercado.txt





