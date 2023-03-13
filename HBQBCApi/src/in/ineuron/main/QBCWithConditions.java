package in.ineuron.main;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;



import in.ineuron.model.Project;
import in.ineuron.util.HibernateUtil;

public class QBCWithConditions {

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
				
				//setting up the parameter 
				ParameterExpression<Long> param1 = builder.parameter(Long.class);
				ParameterExpression<Long> param2 = builder.parameter(Long.class);
			
				//Adding up the condition
				Predicate p1 = builder.ge(root.get("pid"), param1);
				Predicate p2 = builder.le(root.get("pid"), param2);
				
				Predicate p3 = builder.and(p1,p2);
				
				cQuery.where(p3);
				
				//Creating a Order clause
				Order order = builder.desc(root.get("projName"));
				cQuery.orderBy(order);
				
				//Preparing a query having criteria query object
				Query query = session.createQuery(cQuery);
				
				//Setting up the parameters for projId
				query.setParameter(param1,10L );
				query.setParameter(param2,20L );
				
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
