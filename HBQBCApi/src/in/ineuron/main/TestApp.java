package in.ineuron.main;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;



import in.ineuron.model.Project;
import in.ineuron.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) {
		Session session = null;
		
		session = HibernateUtil.getSession();
		
			try {
				//Create criteria builder object
				CriteriaBuilder builder=session.getCriteriaBuilder();
				
				//creating a criteria query object
				CriteriaQuery<Project> cQuery =builder.createQuery(Project.class);
				
				//creating a root object to specify the entity class name
				Root<Project> root = cQuery.from(Project.class);
				
				//adding the root object to Criteria object
				cQuery.select(root);
				
				//Preparing a query having criteria query object
				Query query = session.createQuery(cQuery);
				
				//Executing the JPA QBC logic
				List<Project> list = query.getResultList();
				
				list.forEach(System.out::println);
				
				
				
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				
				HibernateUtil.closeSession(session);
			}

	}

}
