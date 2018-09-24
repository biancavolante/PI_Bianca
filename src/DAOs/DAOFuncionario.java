package DAOs;

import Entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class DAOFuncionario extends DAOGenerico<Funcionario> {

    private List<Funcionario> lista = new ArrayList<>();

    public DAOFuncionario() {
        super(Funcionario.class);
    }

    public int autoFuncionario() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.id) FROM Funcionario e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Funcionario> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Funcionario e WHERE e.id LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Funcionario> listById(int id) {
        return em.createQuery("SELECT e FROM Funcionario e WHERE e.nomeFuncionario= :id").setParameter("id", id).getResultList();
    }

    public List<Funcionario> listInOrderNome() {
        return em.createQuery("SELECT e FROM Funcionario e ORDER BY e.nomeFuncionario").getResultList();
    }

    public List<Funcionario> listInOrderId() {
        return em.createQuery("SELECT e FROM Funcionario e ORDER BY e.id").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Funcionario> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getId() + "-" + lf.get(i).getNomeFuncionario());
        }
        return ls;
    }

    public static void main(String[] args) {
        DAOFuncionario daoFuncionario = new DAOFuncionario();
        List<Funcionario> listaFuncionario = daoFuncionario.list();
        for (Funcionario aluno : listaFuncionario) {
            System.out.println(aluno.getId() + "-" + aluno.getNomeFuncionario());
        }
    }
}
