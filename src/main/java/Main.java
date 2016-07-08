//
//
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import avz.bz.ua.dao.db.DbManager;
//import avz.bz.ua.service.ProductService;
//
//public class Main {
//
//	public static void main(String[] args) {
//		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/SpringBeans.xml");
//         ctx.refresh();
//	        //Get service from context. (service's dependency (ProductDAO) is autowired in ProductService)
//	       DbManager dbManager = ctx.getBean(DbManager.class);
//	       ProductService productService = ctx.getBean(ProductService.class);
//	       
//	       
//	       System.out.println(dbManager.hashCode());
//	       System.out.println(productService.hashCode());
//	   	
//	       
//	       
//	       ctx.close();
//	}
//
//}
