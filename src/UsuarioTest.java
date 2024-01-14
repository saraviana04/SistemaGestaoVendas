import com.culysoft.gestaovenda.modelo.dao.UsuarioDao;
import com.culysoft.gestaovenda.modelo.dominio.Perfil;
import com.culysoft.gestaovenda.modelo.dominio.Usuario;

public class UsuarioTest {

    public static void main(String[]args){
        Usuario usuario = new Usuario();
        UsuarioDao usuarioDao = new UsuarioDao();
        String mensagem = usuario.salvar(usuario);
        System.out.println(mensagem);

    }
}
