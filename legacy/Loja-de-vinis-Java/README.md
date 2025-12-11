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
