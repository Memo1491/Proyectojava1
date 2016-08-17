import java.awt.PointerInfo;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;



public class exa {
	
	
	static ArrayList<Object> eve= new ArrayList<Object>();
	static Scanner in= new Scanner(System.in);
	static base con= new base();
	public static void main(String[] args) {
		
		login();
    }
	
	public static void login()
	{
		int dat;
		Scanner in= new Scanner(System.in);
		int s;
		String user,pdw;
		System.out.println("|--------------------------------|");
		System.out.println("|             ACCESO             |");
		System.out.println("|--------------------------------|");
		  System.out.print("  USUARIOS:");
		  user=in.next();
		  System.out.print("  CONTRASEÑA:");
		  pdw=in.next();
		  dat=con.login(user, pdw);
		  if (dat==0) 
		  {
			  System.out.println("Acceso denegado..");
			  login();
		  }else
		  {
			  exa.Carga_menu(dat);
		  }
		 
		  
	}
	public static String str(String dato,int cantidad)
	{
		while (dato.length()<cantidad)
		{
			dato=dato+" ";
		}
		return dato;
	}
	
	public static void Carga_menu(int lv)
	{
		
		 String clv="";
		 String nombre="";
		 String ap="";
		 String am="";
		 String dir="";
		 String tel="";
		 String puesto="";
		 ArrayList<String>datos;
		 int hr=40;
		 String s;
		 double shr=0;
		 double stodia=100;
		 double seguro=0;
		 double isr=0;
		int seleccion=0;
		if (lv==1) 
		{
			System.out.println("|--------------------------------|");
			System.out.println("|            SISTEMAS            |");
			System.out.println("|--------------------------------|");
			System.out.println("|  1. USUARIOS                   |");
			System.out.println("|  2. SALIR                      |");
			System.out.println("|--------------------------------|");
			System.out.print("     OPCION:");
			seleccion=in.nextInt();
			if (seleccion==1) 
			{
				usuario e= new usuario();
				e.menu(lv);
			}
			else
			{
				if (seleccion==2)
				{
					exa.login();
				}
			}
			
		}
		else
		{
			if (lv==2) 
			{
				System.out.println("|--------------------------------|");
				System.out.println("|        RECURSOS HUMANOS        |");
				System.out.println("|--------------------------------|");
				System.out.println("|  1. EMPLEADOS                  |");
				System.out.println("|  2. CALCULAR NOMINA            |");
				System.out.println("|  3. SALIR                      |");
				System.out.println("|--------------------------------|");
				System.out.print("     OPCION:");
				seleccion=in.nextInt();
				if (seleccion==1) 
				{
					Empleado e= new Empleado();
					e.menu(lv);
				}
				else
				{
					if (seleccion==2)
					{
						
					}
					else
					{
						if (seleccion==3) 
						{
							exa.login();
						}
					}
				}
				
			}
			else	
			{
				if(lv==3)
				{
					System.out.println("|--------------------------------|");
					System.out.println("|           EMPLEADOS            |");
					System.out.println("|--------------------------------|");
					System.out.println("|  1. INFORMACION PERSONAL       |");
					System.out.println("|  2. VER NOMINA                 |");
					System.out.println("|  3. USAR MOTOR                 |");
					System.out.println("|  4. SALIR                      |");
					System.out.println("|--------------------------------|");
					System.out.print("     OPCION:");
					seleccion=in.nextInt();
					if (seleccion==1) 
					{
						String se="";
						do
						{
							System.out.print("     NUMERO DE EMPLEADO:");
							datos=con.Consultar("SELECT e.clave,e.nombre,e.ap_paterno,e.ap_materno,e.telefono,e.direccion,e.puesto FROM empleados e INNER JOIN puestos p on p.puesto=e.puesto WHERE e.clave="+in.next());
							System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
							System.out.println("|                                                 REPOTE DE EMPLEADOS                                                                |");
							System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
							System.out.println("|N° EMPLEADO       |NOMBRE            |APELLIDO PATERNO  |APELLIDO MATERNO  |TELEFONO          |DIRECCION         |PUESTO            |");
							System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
							if (!datos.isEmpty()) 
							{
								clv=datos.get(0);
								nombre=datos.get(1);
								ap=datos.get(2);
								am=datos.get(3);
								tel=datos.get(4);
								dir=datos.get(5);
								puesto=datos.get(6);
								
								System.out.println("|"+str(clv,18)+"|"+str(nombre,18)+"|"+str(ap,18)+"|"+str(am,18)+"|"+str(tel,18)+"|"+str(dir,18)+"|"+str(puesto,18)+"|");
								
							}
							else
							{
							System.out.println("|No se encontro al empleado                                                                                                          |");
							}
							System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
							System.out.println(" NUEVA BUSQUEDA? S/N");
							se=in.next();
						}
						while(se.contains("s")||se.contains("S"));
						Carga_menu(lv);

					}
					else
					{
						if (seleccion==2)
						{
							s="";
							do 
							{
								System.out.print("     NUMERO DE EMPLEADO:");
								datos=con.ConsultarNomina("SELECT e.clave,e.nombre,e.ap_paterno,e.ap_materno,p.sueldoXH FROM empleados e INNER JOIN puestos p on p.puesto=e.puesto WHERE e.clave="+in.next());
								
								if (!datos.isEmpty()) 
								{
									clv=datos.get(0);
									nombre=datos.get(1);
									ap=datos.get(2);
									am=datos.get(3);
									shr=Double.parseDouble(datos.get(4));
									Date d=con.get_Maxfecha();
									
									datos=con.ConsultarUs("SELECT n.fecha,d.ht,d.sxh,d.seguro,d.isr FROM nomina n INNER JOIN desc_nomina d on d.id_nomina=n.id_nom"+
											" WHERE d.id_empleado='"+clv+"' AND n.fecha BETWEEN '2016-08-01' AND '2016-08-15'");
									
									if(!datos.isEmpty())
									{
										hr=Integer.parseInt(datos.get(1));
										shr=Double.parseDouble(datos.get(2));
										seguro=Double.parseDouble(datos.get(3));
										isr=Double.parseDouble(datos.get(4));
									}
									
										/*SELECT IFNULL(EXTRACT(
	hour FROM TIMEDIFF(
	(SELECT hora FROM tiempos WHERE estatus=4 AND fecha='2016-07-01' AND id_usuarios=11311194),
	(SELECT hora FROM tiempos WHERE estatus=1 AND fecha='2016-07-01' AND id_usuarios=11311194)
	)
	),0) as horas*/
									System.out.println("|---------------------------------------------------------------------------|");
									System.out.println("|                                  NOMINA                                   |");
									System.out.println("|---------------------------------------------------------------------------|");
									System.out.println("|N° EMPLEADO       |NOMBRE            |APELLIDO PATERNO  |APELLIDO MATERNO  |");
									System.out.println("|---------------------------------------------------------------------------|");
									System.out.println("|"+str(clv,18)+"|"+str(nombre,18)+"|"+str(ap,18)+"|"+str(am,18)+"|");
									System.out.println("|---------------------------------------------------------------------------|");
									System.out.println("|          PRECEPCIONES               |           DEDUCCIONES               |");
									System.out.println("|---------------------------------------------------------------------------|");
									System.out.println("|HORAS TRABAJADAS             "+str(""+hr,8)+"| SEGURO                     $"+str(""+seguro,8)+"|");
									System.out.println("|SUELDO POR HORAS            $"+str(""+shr,8)+"| ISR(12.84%)                $"+str(""+isr,8)+"|");
									System.out.println("|SEPTIMO DIA                 $"+str(""+stodia,8)+"|                                     |");
									System.out.println("|---------------------------------------------------------------------------|");
									System.out.println("|                                                              |   $"+str(""+((((hr*shr)+stodia)-seguro)-isr),8)+"|");
									System.out.println("|---------------------------------------------------------------------------|");
									
								}
								System.out.println("NUEVA BUSQUEDA? S/N");
								s=in.next();
						} while (s.contains("s")||s.contains("S"));
							Carga_menu(lv);
							
						}
						else
						{
							if (seleccion==3) 
							{
								System.out.print("     NUMERO DE EMPLEADO:");
								motor m= new motor();
								m.setEmpleado(in.next());
								do {
									System.out.print("1-G. izquierda 2-G. derecha 3-Detener 4-Salir");
									int dato=in.nextInt();
									if (dato==1) 
									{
										if (m.G_der()) 
										{
											System.out.print("Motor Girando a la derecha");
										}
									}
									else
									{
										if (dato==2)
										{
											if (m.G_izq()) 
											{
												System.out.print("Motor Girando a la izquierda");
											}
										}
										else
										{
											if (dato==3)
											{
												if (m.stop()) 
												{
													System.out.print("Motor Detenido");
												}
											}
										}
									}
									
									System.out.print("Nuevo movimiento? S/N");
									s=in.next();
								} while (s.contains("s")||s.contains("S"));
								
							}
							else
							{
								if (seleccion==4) 
								{
									exa.login();
								}
							}
						}
						
					}
					
					
				}
			}
		}
	}
	
	public void buscar(int lv)
	{
		
	}
	public void nuevo(int nv,String actividad)
	{
		
	}
	public boolean modificar(String table,Object[] datos,String[] campos,String id_camp,String id)
	{
		return con.update(table, datos, campos, id_camp, id);
	}
	public boolean eliminar(String clave,String tabla,String campo)
	{
		
		return con.delete(clave, tabla, campo);
	}
	public boolean agregar(String table,Object[] datos,String[] campos)
	{
		return con.insert(datos, campos, table);
	}
	public void  menu(int nv)
	{
		
	}
	
}




class motor
{
  
	
	 //
	base b;
	
	 
	public motor() 
	{
		
		b= new base();
		conectar();
	}
	
	private String empleado;
	
	private boolean conectar() 
	{
		
		return false;
		
	}
	

	public void setEmpleado(String empleado) 
	{
		this.empleado = empleado;
	}
	
	public boolean G_izq() 
	{
		if (conectar())
		{
			if(b.executarSql("insert into bitacora values(null,'11311194','Girando a la izquierda',now(),now())"))
			{
				System.out.println("Motor Girando a la Izquierda");
				
				return true;
			}
		}
		else
		{
			System.out.println("Motor desconectado...");
		}
		return false;
	}
	
	public boolean G_der() 
	{
		if (conectar())
		{
			;
			if(b.executarSql("insert into bitacora values(null,'11311194','Girando a la derecha',now(),now())"))
			{
				System.out.println("Motor Girando a la Izquierda");
				return true;
			}
		}
		else
		{
			System.out.println("Motor desconectado...");
		}
		return false;
	}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               	public boolean stop() 
	{
		if (conectar())
		{
			if(b.executarSql("insert into bitacora values(null,'11311194','Detenido',now(),now())emple"))
			{
				System.out.println("Motor detenido");
				return true;
			}
		}
		else
		{
			System.out.println("Motor desconectado...");
		}
		return false;
	}
	
	public void imprimirVitacora(String empleado)
	{
		String sql="select * from bitacora where clave='"+empleado+"'";
		ResultSet rs = b.getbitacora(sql); 
		/*while (rs.next()) 
		{
			System.out.println("|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"); 
        }*/
	}
	
	
	public void imprimirVitacora(String desde,String hasta)
	{
		String sql="select * from bitacora where clave='"+empleado+"'";
		ResultSet rs = b.getbitacora(sql); 
		/*while (rs.next()) 
		{
			System.out.println("|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"); 
        }¨*/
	}
	
	
	public void imprimirVitacora(String empleado,String desde,String hasta)
	{
		String sql="select * from bitacora where clave='"+empleado+"'";
		ResultSet rs = b.getbitacora(sql); 
		/*while (rs.next()) 
		{
			System.out.println("|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"); 
        }*/
	}
	
	
	public void imprimirVitacora()
	{
		String sql="select * from bitacora";
		ResultSet rs = b.getbitacora(sql); 
		/*while (rs.next()) 
		{
			System.out.println("|"+rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"|"); 
        }*/
	}
}



class Empleado extends exa 
{
	private String clv="";
	private String nombre=" ";
	private String ap="";
	private String am="";
	private String dir=" ";
	private String tel="";
	private String puesto="";
	int seleccion=0;
	String se="";
	public String str2(String dato)
	{
		while (dato.length()<18)
		{
			dato=dato+" ";
		}
		return dato;
	}
	@Override
	public void buscar(int lv) 
	{
		String se;
		
		do {
			
			seleccion=0;
			System.out.print("     NUMERO DE EMPLEADO:");
			ArrayList<String>dt=con.Consultar("select * from empleados where clave='"+in.next()+"'");
			
			///aqui se hara la busqueda
			System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
			System.out.println("|                                                  REPOTE DE EMPLEADOS                                                               |");
			System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
			System.out.println("|N° EMPLEADO       |NOMBRE            |APELLIDO PATERNO  |APELLIDO MATERNO  |TELEFONO          |DIRECCION         |PUESTO            |");
			System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
			if(!dt.isEmpty())
			{
				clv=dt.get(0);
				nombre=dt.get(1);
				ap=dt.get(2);
				am=dt.get(3);
				tel=dt.get(4);
				dir=dt.get(5);
				puesto=dt.get(6);
				System.out.println("|"+str2(clv)+"|"+str2(nombre)+"|"+str2(ap)+"|"+str2(am)+"|"+str2(tel)+"|"+str2(dir)+"|"+str2(puesto)+"|");
				System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
				System.out.println("|1 MODIFICAR       |2 ELIMINAR        |3 SALIR           |                  |                  |                  |                  |");
				System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
			}
			else
			{
				System.out.println("|No se encotro al empleado                                                                                                           |");
				System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
				System.out.println("|3 SALIR           |                  |                  |                  |                  |                  |                  |");
				System.out.println("|------------------------------------------------------------------------------------------------------------------------------------|");
			}
			
			
			
			System.out.print("     OPCION:");
			seleccion=in.nextInt();
			if (seleccion==1)
			{
				String[]cam={"nombre","ap_paterno","ap_materno","telefono","direccion","puesto"};
				nuevo(lv,"MODIFICAR");
				
				System.out.println(" DESEA GUARDAR ESTA INFORMACION S/N");
				System.out.print("     OPCION:");
				se=in.next();
				if (se.contains("s")||se.contains("S"))
				{
					Object[]datos={this.nombre,this.ap,this.am,this.tel,this.dir,this.puesto};
					if(modificar("empleados", datos, cam,"clave" , this.clv))
					{
						System.out.println("EMPLEADO GUARDADO CON EXITO");
					}
					else
					{
						System.out.println("ERROR AL GUARDAR");
					}
				}
				else
				{
					
					System.out.println("CANCELADO..");
				}
				
			}
			else
			{
				if (seleccion==2) 
				{
					System.out.println(" DESEA ELIMINAR ESTA INFORMACION S/N");
					System.out.print("     OPCION:");
					se=in.next();
					if (se.contains("s")||se.contains("S"))
					{
						if (eliminar(this.clv,"empleados","clave")) 
						{
							System.out.println("EMPLEADO ELIMINADO CON EXITO");
						}
						else
						{
							System.out.println("ERROR AL ELIMINAR");
						}
					}
					else
					{
						System.out.println("CANCELADO..");
					}
				}
				else
				{
					if (seleccion==3)
					{
						
					}
					else
					{
						System.out.print("     NO ESTA EN EL MENU DE OPCIONES");
					}
				}
			}
			System.out.println("     NUEVA BUSQUEDA S/N?");
			se=in.next();
		} while (se.contains("S")||se.contains("s"));
		
		menu(lv);
		
	}
	
	@Override
	public void menu(int nv) 
	{
		int seleccion=0;
		String se;
		System.out.println("|--------------------------------|");
		System.out.println("|            EMPLEADOS           |");
		System.out.println("|--------------------------------|");
		System.out.println("|  1. BUSCAR                     |");
		System.out.println("|  2. NUEVO                      |");
		System.out.println("|  3. SALIR                      |");
		System.out.println("|--------------------------------|");
		System.out.print("     OPCION:");
		seleccion=in.nextInt();
		if (seleccion==1)
		{
			buscar(nv);	
		}
		else
		{
			if (seleccion==2) 
			{
				do {
				nuevo(nv,"ALTAS");
				String[]cam={"nombre","ap_paterno","ap_materno","telefono","direccion","puesto"};
				Object[]datos={this.nombre,this.ap,this.am,this.tel,this.dir,this.puesto};
				
				System.out.println(" DESEA GUARDAR ESTA INFORMACION S/N");
				System.out.print("     OPCION:");
				se=in.next();
				if (se.contains("s")||se.contains("S"))
				{
					if (agregar("usuarios",datos,cam)) 
					{
						System.out.println("EMPLEADO GUARDADO CON EXITO");
					}
					else
					{
						System.out.println("ERROR AL GUARDAR");
					}
				}
				else
				{
					System.out.println("CANCELADO..");
				}
				System.out.println("DESEA AGREGAR OTRO EMPLEADO? S/N");
				se=in.next();
				}
				while (se.contains("s")||se.contains("S"));
				menu(nv);
			}
			else
			{
				if (seleccion==3) 
				{
					exa.Carga_menu(nv);
				}
				else
				{
					menu(nv);
				}
				
			}
		}
	}

	@Override
	public void nuevo(int nv,String actividad) {
		
			System.out.println("|-----------------------------|");
			System.out.println("|     "+actividad+" DE EMPLEADO    |");
			System.out.println("|-----------------------------|");
			System.out.print("|NOMBRES :");
			this.nombre= in.next();
			System.out.print("|APELLIDO PATERNO :");
			this.ap=in.next();
			System.out.print("|APELLIDO MATERNO :");
			this.am=in.next();
			System.out.print("|TELEFONO:");
			this.tel=in.next();
			System.out.print("|DIRECCION:");
			this.dir=in.next();
			System.out.print("|PUESTO:");
			this.puesto=in.next();
			System.out.println("|-----------------------------|");
	}
}

class usuario extends exa
{
	private int id_us;
	private String usuario;
	private String Contraseña;
	private String nem;
	private int nivel;
	
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
	
	
	
	int seleccion;
	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}
	public void setUsuario(String usuarios) {
		this.usuario = usuarios;
	}

	@Override
	public void menu(int nv) {
		int seleccion=0;
		String se;
		System.out.println("|--------------------------------|");
		System.out.println("|            USUARIOS            |");
		System.out.println("|--------------------------------|");
		System.out.println("|  1. BUSCAR                     |");
		System.out.println("|  2. NUEVO                      |");
		System.out.println("|  3. SALIR                      |");
		System.out.println("|--------------------------------|");
		System.out.print("     OPCION:");
		seleccion=in.nextInt();
		if (seleccion==1)
		{
			buscar(nv);	
		}
		else
		{
			if (seleccion==2) 
			{
				do {
					
					nuevo(nv,"ALTAS");
					System.out.println(" DESEA GUARDAR ESTA INFORMACION S/N");
					System.out.print("     OPCION:");
					se=in.next();
					String[] camp={"usuario","pdw","nivel","id_empleado"};
					Object[]dat={this.usuario,this.Contraseña,this.nivel,this.nem};
					if (se.contains("s")||se.contains("S"))
					{
						if (agregar("usuarios",dat,camp))
						{
							
							System.out.println("EMPLEADO GUARDADO CON EXITO");
						}
						else
						{
							System.out.println("ERROR AL GUARDAR");
						}
					}
					else
					{
						System.out.println("CANCELADO..");
					}
					
					System.out.println("DESEA AGREGAR OTRO EMPLEADO? S/N");
					se=in.next();
					} while (se.contains("s")||se.contains("S"));
					menu(nv);
				
			}
			else
			{
				if (seleccion==3) 
				{
					exa.Carga_menu(nv);
				}
				else
				{
					menu(nv);
				}
				
			}
		}
			
	}
	
	@Override
	public void nuevo(int lv,String acc) 
	{
		
		String se="";
		System.out.println("|-----------------------------|");
		System.out.println("|     "+acc+" DE EMPLEADO    |");
		System.out.println("|-----------------------------|");
		System.out.print("|USUARIO :");
		this.usuario= in.next();
		System.out.print("|CONTRASEÑA :");
		this.Contraseña=MD5(in.next());
		System.out.print("|NIVEL :");
		this.nivel=in.nextInt();
		System.out.print("|N° EMPLEADO :");
		this.nem=in.next();
		System.out.println("|-----------------------------|");
	}
	
	
	public String str2(String dato)
	{
		while (dato.length()<16)
		{
			dato=dato+" ";
		}
		return dato;
	}
	
	@Override
	public void buscar(int lv) 
	{
		String se="";
		do {
				
				seleccion=0;
				System.out.print("     NOMBRE DE USUARIO:");
				ArrayList<String>dt=con.ConsultarUs("SELECT * FROM usuarios WHERE usuario='"+in.next()+"'");
				System.out.println("|-------------------------------------------------------------------|");
				System.out.println("|                        REPOTE DE USUARIOS                         |");
				System.out.println("|-------------------------------------------------------------------|");
				System.out.println("|ID              |USUARIO         |NIVEL           |N° EMPLEADO     |");
				System.out.println("|-------------------------------------------------------------------|");
				if (!dt.isEmpty()) 
				{
					this.id_us=Integer.parseInt(dt.get(0));
					this.usuario=dt.get(1);
					this.nem=dt.get(3);
					this.nivel= Integer.parseInt(dt.get(4));
					
					System.out.println("|"+str2(""+this.id_us)+"|"+str2(this.usuario)+"|"+str2(this.nem)+"|"+str2(""+this.nivel)+"|");
					System.out.println("|-------------------------------------------------------------------|");
					System.out.println("|1 REST. CLAVE   |2 MODIFICAR     |3 ELIMINAR      |4 SALIR         |");
					System.out.println("|-------------------------------------------------------------------|");
					
					
				}
				else
				{
					System.out.println("|No se encontro al usuario                                          |");
					System.out.println("|-------------------------------------------------------------------|");
					System.out.println("|4 SALIR         |                |                |                |");
					System.out.println("|-------------------------------------------------------------------|");
				}
				System.out.print("     OPCION:");
				seleccion=in.nextInt();
				if (seleccion==1)
				{
					String[] camp={"pdw"};
					System.out.println(" NUEVA CONTRASEÑA:");
					Object[]dat={MD5(in.next())};
					System.out.println(" DESEA GUARDAR ESTA INFORMACION S/N");
					System.out.print("     OPCION:");
					se=in.next();
					if (se.contains("s")||se.contains("S"))
					{
						
						if (modificar("usuarios", dat, camp,"id_usuario",""+this.id_us))
						{
							System.out.println("EMPLEADO GUARDADO CON EXITO");
						}
						else
						{
							System.out.println("ERROR AL GUARDAR");
						}
					}
					else
					{
						System.out.println("CANCELADO..");
					}
				}
				else
				{
					if (seleccion==2)
					{
						nuevo(lv, "MODIFICAR");
						
						
						System.out.println(" DESEA GUARDAR ESTA INFORMACION S/N");
						System.out.print("     OPCION:");
						se=in.next();
						String[] camp={"usuario","nivel","id_empleado"};
						Object[]dat={this.usuario,this.nivel,this.nem};
						if (se.contains("s")||se.contains("S"))
						{
							
							if (modificar("usuarios", dat, camp,"id_usuario",""+this.id_us))
							{
								System.out.println("EMPLEADO GUARDADO CON EXITO");
							}
							else
							{
								System.out.println("ERROR AL GUARDAR");
							}
						}
						else
						{
							System.out.println("CANCELADO..");
						}
						
					}
					else
					{
						if (seleccion==3) 
						{
							System.out.println(" DESEA ELIMINAR ESTA INFORMACION S/N");
							System.out.print("     OPCION:");
							se=in.next();
							if (se.contains("s")||se.contains("S"))
							{
								if (eliminar(""+this.id_us,"usuarios","id_usuario")) 
								{
									System.out.println("USUARIO ELIMINADO CON EXITO");
								}
								else
								{
									System.out.println("ERROR AL ELIMINAR");
								}
							}
							else
							{
								System.out.println("CANCELADO..");
							}
						}
						else
						{
							if (seleccion==4)
							{
								menu(lv);
							}else
							{
								buscar(lv); 
							}
							
						}
					}
				}
				System.out.println("     NUEVA BUSQUEDA S/N?");
				se=in.next();
		} while (se.contains("S")||se.contains("s"));
		buscar(lv); 
	}
	
}






















class base
{
	String cadena="";
	Connection conexion = null;
	
	public base()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/empleados", "root", "123");
		}
		catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
		catch (ClassNotFoundException e) 
        {
            System.out.println(e.getMessage());
        } 
	}

	public Date get_Maxfecha()
	{
		try
		{
			Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery("SELECT MAX(fecha)Ultima FROM nomina"); 
	        while (rs.next()) 
			{
	        	return rs.getDate(1);
            }
	        return null;
		}
		catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return null;
        } 
	}
	 
	public int get_horasT()
	{
		try
		{
			Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery(""); 
	        while (rs.next()) 
			{
	        	return rs.getInt(1);
            }
	        return 0;
		}
		catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return 0;
        } 
	}
	
	
	public int login(String user, String cla)
	{
		try
		{
			Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery("SELECT Nivel FROM usuarios WHERE usuario='"+user+"' AND pdw=MD5('"+cla+"')"); 
	        while (rs.next()) 
			{
	        	return rs.getInt(1);
            }
	        return 0;
		}
		catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return 0;
        } 
	}
	
	public ArrayList<String> ConsultarUs(String sql)
	{
		
		ArrayList<String> datos= new ArrayList<String>();
		try
		{
			Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery(sql); 
	        while (rs.next()) 
			{
	        	datos.add(rs.getString(1));
	        	datos.add(rs.getString(2));
	        	datos.add(rs.getString(3));
	        	datos.add(rs.getString(4));
	        	datos.add(rs.getString(5));
                
            }
	        return datos;

		}
		catch (SQLException e) 
        {
			
            System.out.println(e.getMessage());
            return null;
        } 
		
	}
	
	
	public ArrayList<String> ConsultarNomina(String sql)
	{
		
		ArrayList<String> datos= new ArrayList<String>();
		try
		{
			Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery(sql); 
	        while (rs.next()) 
			{
	        	datos.add(rs.getString(1));
	        	datos.add(rs.getString(2));
	        	datos.add(rs.getString(3));
	        	datos.add(rs.getString(4));
	        	datos.add(rs.getString(5));
                
            }
	        return datos;

		}
		catch (SQLException e) 
        {
			
            System.out.println(e.getMessage());
            return null;
        } 
		
	}
	
	
	
	public ArrayList<String> Consultar(String sql)
	{
		
		ArrayList<String> datos= new ArrayList<String>();
		try
		{
			Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery(sql); 
	        while (rs.next()) 
			{
	        	datos.add(rs.getString(1));
	        	datos.add(rs.getString(2));
	        	datos.add(rs.getString(3));
	        	datos.add(rs.getString(4));
	        	datos.add(rs.getString(5));
	        	datos.add(rs.getString(6));
	        	datos.add(rs.getString(7));
            }
	        return datos;

		}
		catch (SQLException e) 
        {
			
            System.out.println(e.getMessage());
            return null;
        } 
		
	}
	public boolean executarSql(String sql)
	{
		try
		{
			Statement s = conexion.createStatement();
			if(s.executeUpdate(sql)>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return false;
        } 
	}

	public boolean insert(Object[] datos,String[] campos,String tabla)
	{
		cadena="insert into "+tabla+" values(null,";
		for (int i = 0; i < datos.length; i++) 
		{
			if (i==datos.length-1)
			{
				
				if (datos[i] instanceof String) 
				{
					cadena=cadena+"'"+datos[i]+"')";
				}
				else
				{
					cadena=cadena+""+datos[i]+")";
				}
				
			}
			else
			{
				if (datos[i] instanceof String) 
				{
					cadena=cadena+"'"+datos[i]+"',";
				}
				else
				{
					cadena=cadena+datos[i]+",";
				}
			}
			
		}
		return executarSql(cadena);
	}
	

	public boolean delete(String id,String tabla,String campo)
	{
		return executarSql("delete from "+tabla +" where "+campo+"="+id);
	}
	
	public boolean update(String table,Object[] datos,String[] campos,String id_camp,String id)
	{
		cadena="update "+table+" set ";
		for (int i = 0; i < campos.length; i++)
		{
			 
			if (i==datos.length-1)
			{
				
				if (datos[i] instanceof String) 
				{
					cadena=cadena+campos[i]+"='"+datos[i]+"'";
				}
				else
				{
					cadena=cadena+campos[i]+"="+datos[i]+"";
				}
				
			}
			else
			{
				if (datos[i] instanceof String) 
				{
					cadena=cadena+campos[i]+"='"+datos[i]+"',";
				}
				else
				{
					cadena=cadena+campos[i]+"="+datos[i]+",";
				}
			}
			
			
		}
		cadena=cadena+" where "+id_camp+"="+id;
		return executarSql(cadena);
	}
	
	
	public ResultSet getbitacora(String sql)
	{
		try
		{
			Statement s = conexion.createStatement();
	        ResultSet rs = s.executeQuery(sql); 
	        return rs;

		}
		catch (SQLException e) 
        {
            return null;
        } 
	}
	
	
	
	
	
	public void Conectar()
	{
		
        try {
            // Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");

            // Se obtiene una conexión con la base de datos.
            // En este caso nos conectamos a la base de datos prueba
            // con el usuario root y contraseña 1daw
        	

            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs 
            ResultSet rs = s.executeQuery("select * from usuarios");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }
        } catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        { // Se cierra la conexión con la base de datos.
            try 
            {
                if (conexion != null) 
                {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}
}