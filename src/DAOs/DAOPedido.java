package DAOs;

import Entidades.Pedido;
import java.util.ArrayList;
import java.util.List;

public class DAOPedido extends DAOGenerico<Pedido> {

    private List<Pedido> lista = new ArrayList<>();

    public DAOPedido() {
        super(Pedido.class);
    }

    public int autoIdPedido() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPedido) FROM Pedido e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

//    public List<Pedido> listByNome(String nome) {
//        return em.createQuery("SELECT e FROM Pedido e WHERE e.idPedido LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
//    }

    public List<Pedido> listById(int id) {
        return em.createQuery("SELECT e FROM Pedido e WHERE e.nomePedido= :id").setParameter("id", id).getResultList();
    }

//    public List<Pedido> listInOrderNome() {
//        return em.createQuery("SELECT e FROM Pedido e ORDER BY e.nomePedido").getResultList();
//    }

    public List<Pedido> listInOrderId(String id) {
        return em.createQuery("SELECT e FROM Pedido e ORDER BY e.idPedido").getResultList();
    }

//    public List<String> listInOrderNomeStrings(String qualOrdem) {
//        List<Pedido> lf;
//        if (qualOrdem.equals("id")) {
//            lf = listInOrderId();
//        } else {
//            lf = listInOrderNome();
//        }
//
//        List<String> ls = new ArrayList<>();
//        for (int i = 0; i < lf.size(); i++) {
//            ls.add(lf.get(i).getIdPedido() + "-" + lf.get(i).getNomePedido());
//        }
//        return ls;
//    }

    public static void main(String[] args) {
        DAOPedido daoPedido = new DAOPedido();
        List<Pedido> listaPedido = daoPedido.list();
        for (Pedido aluno : listaPedido) {
            System.out.println(aluno.getIdPedido());
        }
    }
}

    
