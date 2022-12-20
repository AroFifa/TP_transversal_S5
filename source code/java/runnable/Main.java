package runnable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws Exception {
//		String str="str_hahaa sklaa";
//		boolean b=str.contains("strh");
		SpringApplication.run(Main.class, args);
//
//		Vehicule m =new Vehicule();
//
//
//		ConnectDb con= null;
//		try {
//			con = Utilitaire.getConnection(m);
//			GenericDao dao=new GenericDao();
//		List<Object> list=m.find("and",con);
//		for (Object o :
//				list) {
//			m=(Vehicule) o;
//			System.out.println(m.getModel().getModel()+" , "+m.getModel().getMarque().getMarque()+" , "+m.getBoite().getType_boite());
//
//		}
//		} catch (ExceptionDao e) {
//			throw e;
//		} catch (SQLException e) {
//			throw e;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			con.close();
//		}
//

	}

}
