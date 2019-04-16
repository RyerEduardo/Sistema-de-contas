package br.com.exemplologin.model;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conta.class)
public abstract class Conta_ {

	public static volatile SingularAttribute<Conta, Integer> vezes;
	public static volatile SingularAttribute<Conta, Double> valor;
	public static volatile SingularAttribute<Conta, String> titulo;
	public static volatile SingularAttribute<Conta, Boolean> parcelado;
	public static volatile SingularAttribute<Conta, Usuario> usuario;
	public static volatile SingularAttribute<Conta, Integer> id;
	public static volatile SingularAttribute<Conta, Calendar> dataCompra;

}

