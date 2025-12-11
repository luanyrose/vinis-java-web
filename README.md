<h2>Diagrama UML – Loja de Vinis</h2>
<p>
O diagrama UML representa a estrutura principal do sistema <strong>Loja de Vinis</strong>,
mostrando as classes, atributos e relações entre elas. Ele segue um modelo orientado a
objetos, dividindo as responsabilidades entre <em>pessoas</em>, <em>itens (vinis)</em> e
<em>operações comerciais</em>.
</p>

<h3>Classes de Pessoas</h3>
<table>
  <tr><th>Classe</th><th>Atributos</th><th>Descrição</th></tr>
  <tr>
    <td><strong>Pessoa</strong></td>
    <td>nome, cpf, email</td>
    <td>Classe base que representa qualquer pessoa no sistema.</td>
  </tr>
  <tr>
    <td><strong>Cliente</strong> (extends Pessoa)</td>
    <td>tipoCliente, historicoCompras</td>
    <td>Representa o cliente, com histórico de compras.</td>
  </tr>
  <tr>
    <td><strong>Funcionario</strong> (extends Pessoa)</td>
    <td>cargo, salario</td>
    <td>Representa os funcionários da loja.</td>
  </tr>
</table>

<h3>Classes de Itens e Operações</h3>
<table>
  <tr><th>Classe</th><th>Atributos</th><th>Descrição</th></tr>
  <tr>
    <td><strong>Vinil</strong></td>
    <td>codigo, titulo, artista, precoVenda, genero, qtdDisponivel</td>
    <td>Define cada disco disponível na loja.</td>
  </tr>
  <tr>
    <td><strong>Estoque</strong></td>
    <td>vinis: List&lt;Vinil&gt;</td>
    <td>Gerencia os vinis disponíveis e suas quantidades.</td>
  </tr>
</table>

<h3>Classes de Transações</h3>
<table>
  <tr><th>Classe</th><th>Atributos</th><th>Descrição</th></tr>
  <tr>
    <td><strong>Compra</strong></td>
    <td>dataCompra, cliente, valorTotal, itens: List&lt;ItemCompra&gt;</td>
    <td>Representa uma compra realizada por um cliente.</td>
  </tr>
  <tr>
    <td><strong>ItemCompra</strong></td>
    <td>vinil, quantidade, valorItem</td>
    <td>Detalha cada item dentro de uma compra.</td>
  </tr>
</table>

<h3>Relações Importantes</h3>
<ul>
  <li><strong>Herança:</strong> Cliente e Funcionario herdam de Pessoa.</li>
  <li><strong>Composição:</strong> Compra contém vários ItemCompra (1..*).</li>
  <li><strong>Associação:</strong> Cliente realiza Compras.</li>
  <li><strong>Agregação:</strong> Estoque possui vários Vinis.</li>
</ul>

<h3>Grupo</h3>

Luany Rose Lima de Mélo<br>
Raquel Mariana Silva Nascimento<br>
Zion Henrique Parcelino Cordeiro Germano<br>

**Legacy: Projeto Java (exemplo console)**

- **Pasta:** `legacy/Loja-de-vinis-Java`
- Este diretório contém uma versão Java clássica (console) da Loja de Vinis
  com classes em pacote default (`Main.java`, `model/`, `dao/`, `util/`).
- Não foi mesclado diretamente com o projeto Spring Boot para evitar
  conflitos com as entidades JPA já existentes em `src/main/java`.

Como usar o código legado (opções):

- Compilar e executar com JDK (linha de comando):
```
javac legacy\\Loja-de-vinis-Java\\Main.java -d legacy\\Loja-de-vinis-Java\\out
java -cp legacy\\Loja-de-vinis-Java\\out Main
```

- Ou abrir a pasta `legacy/Loja-de-vinis-Java` na sua IDE (IntelliJ/Eclipse)
  e executar `Main` como aplicação Java console.

Observação: o projeto Spring Boot principal (`vinis-java-web`) já contém
entidades JPA, repositórios e controladores. O código em `legacy/` foi
preservado como referência/backup. Se quiser que eu converta os DAOs
antigos para usar Spring Data JPA e integrar funcionalmente, posso fazer.
