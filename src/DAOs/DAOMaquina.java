package DAOs;


import Entidades.Maquina;
import DAOs.DAOGenerico;
import static DAOs.DAOGenerico.em;
import java.util.ArrayList;
import java.util.List;

public class DAOMaquina extends DAOGenerico<Maquina> {

    public DAOMaquina() {
        super(Maquina.class);
    }

    public int autoIdMaquina() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idMaquina) FROM Maquina e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Maquina> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Maquina e WHERE e.nomeMaquina LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Maquina> listById(int id) {
        return em.createQuery("SELECT e FROM Maquina e WHERE e.idMaquina = :id").setParameter("id", id).getResultList();
    }

    public List<Maquina> listInOrderNome() {
        return em.createQuery("SELECT e FROM Maquina e ORDER BY e.nomeMaquina").getResultList();
    }

    public List<Maquina> listInOrderId() {
        return em.createQuery("SELECT e FROM Maquina e ORDER BY e.idMaquina").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Maquina> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdMaquina() + "-" + lf.get(i).getNomeMaquina());
        }
        return ls;
    }
public static void main(String[] args) {
        DAOMaquina daoMaquina = new DAOMaquina();
        List<Maquina> listaMaquina = daoMaquina.list();
        for (Maquina maquina : listaMaquina) {
            System.out.println(maquina.getIdMaquina() + "-" + maquina.getNomeMaquina());
        }
    }}
