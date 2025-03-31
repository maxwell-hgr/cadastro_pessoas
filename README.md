# 🚀 Cadastro de Pessoas com EJB e JSF

## 📌 Sobre o Projeto
Segui o modelo MVC para deixar a aplicação bem organizada e fácil de entender na avaliação. Também implementei todas as tecnologias pedidas no desafio, incluindo injeção de dependências com EJB.

No começo, usei o Tomcat porque já estava mais acostumado com CDI, mas depois resolvi encarar o desafio de trabalhar com EJB no WildFly. Para a interface, segui a sugestão do desafio e escolhi o 
PrimeFaces, que tem componentes bem avançados e estilos prontos, facilitando o desenvolvimento.

A aplicação é um CRUD de registro de pessoas, onde cada usuário pode ter vários endereços, que são salvos como entidades no banco de dados em um relacionamento um-para-muitos. As funcionalidades 
incluem cadastrar, editar, listar e excluir registros.

## ⚙️ Funcionalidades
- ✏️ **Cadastro completo** de pessoas com múltiplos endereços
- 🔍 **Listagem inteligente** de registros
- 🛠️ **Edição detalhada** de todas as informações
- 🗑️ **Exclusão segura** de registros
- ✅ **Validação robusta** dos dados

## 🛠️ Tecnologias Utilizadas

```
- Java 8
- EJB 3.2
- JSF 2.3.7 
- Hibernate Core 5.2.4
- Hibernate Validator 5.3.2
- Lombok (Redução de boilerplate)
- PrimeFaces 12.0.0

Infra:
- WildFly 9.0.0.Final
- PostgreSQL 16.8
```

## 🚀 Como Executar

### Pré-requisitos
- Java 8
- WildFly 9.0.0.Final
- PostgreSQL 16.8

### Configuração do PostgreSQL + WildFly

#### 1. Instalação do PostgreSQL (Ubuntu/Debian)
```bash
# Atualize os pacotes
sudo apt update

# Instale o PostgreSQL
sudo apt install postgresql postgresql-contrib

# Verifique o status do serviço
sudo systemctl status postgresql
```

#### 2. Criação do Banco e Usuário
```bash
# Acesse o console do PostgreSQL
sudo -u postgres psql

# Dentro do console PSQL execute:
CREATE DATABASE cadastro_pessoas;
CREATE USER app_user WITH PASSWORD 'sua_senha_segura';
GRANT ALL PRIVILEGES ON DATABASE cadastro_pessoas TO app_user;
ALTER DATABASE cadastro_pessoas OWNER TO app_user;
```

#### 3. Configuração Completa do PostgreSQL

```bash
1. Crie a estrutura de pastas:
   WildFly_HOME/modules/system/layers/base/org/postgresql/main

2. Adicione:
   - postgresql-42.7.2.jar
   - module.xml (configuração do driver)

3. Configure o datasource no standalone.xml
```

#### Conteúdo do `module.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.3" name="org.postgresql">
    <resources>
        <resource-root path="postgresql-42.7.2.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
        <module name="javax.servlet.api" optional="true"/>
    </dependencies>
</module>
```

#### Configuração no `standalone.xml`
```xml
<datasource jndi-name="java:/jboss/datasources/PostgreSQLDS" 
            pool-name="PostgreSQLDS" 
            enabled="true" 
            use-java-context="true">
    <connection-url>jdbc:postgresql://localhost:5432/cadastropessoa</connection-url>
    <driver>postgresql</driver>
    <security>
        <user-name>seu_usuario</user-name>
        <password>sua_senha</password>
    </security>
</datasource>
<driver name="postgresql" module="org.postgresql">
  <driver-class>org.postgresql.Driver</driver-class>
  <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
</driver>
```

### Execução via IDE (IntelliJ)

```text
1. File → Settings → Build → Application Servers → Add WildFly
2. Run → Edit Configurations → WildFly/Local
3. Deployment → Add cadastro_pessoasEJB.war
4. Execute o servidor
```

### Execução via Linha de Comando

```bash
mvn clean package
cp target/cadastro_pessoasEJB.war {WildFly_HOME}/standalone/deployments/
./standalone.sh
```

Acesse: http://localhost:8080/cadastro_pessoasEJB

## 📚 Aprendizados

```diff
+ Dominio do ciclo de vida de Managed Beans
+ Redução significativa de boilerplate com EJB
+ Configuração avançada de datasources no WildFly
- Desafio inicial na migração de Tomcat/CDI para WildFly/EJB
```

## 📝 Notas Adicionais

Aprendi bastante sobre o tempo de vida das instâncias dos Managed Beans, especialmente sobre como manipular suas variáveis para obter o resultado esperado.

O CRUD com EJB também se mostrou bem eficiente, reduzindo bastante o código boilerplate, algo que não teria o mesmo efeito usando Tomcat. Procurei 
manter o código o mais simples possível para a avaliação, organizando bem as responsabilidades e separando cada parte do código de forma clara.
