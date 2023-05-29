/* ----------------------------------------------USUARIO--------------------------------------------------------------------------------------------------------------------------------------------------*/
/* USUARIO: Cedula(PK), CORREO, NOMBRE, PASSWORD, DIRECCION, TELEFONO */

insert into usuario values (1, "a@gmail.com", "a", "000", "Cr 10 #25-30, ", "3001234567","");
insert into usuario values (2, "b@hotmail.com", "b", "001", "Cll 30 #8-23,", "3102345678","");
insert into usuario values (3, "c@yahoo.com", "c", "002", "Av 4 #18-15,", "3013456789","");
insert into usuario values (4, "d@gmail.com", "d", "003", "Cr 8 #16-45, ", "3204567890","");
insert into usuario values (5, "e@outlook.com", "e", "004", "Cll 12 #9-60, ", "3155678901","");

/*----------------------------------- Moderador ----------------------------------*/
/* codigo, email, nombre, password */
insert into Moderador values (1, "mod1@outlook.com", "mod", "004" );

/*----------------------------------------------Producto-------------------------------------------------------------------*/
/* Producto:  codigo,activo,descripcion.fecha_creacion ,fecha_limite,nombre,precio,unidades ,moderador_codigo,usuario_codigo*/
/* PRODUCTO: CODIGO(PK), DESCRIPCION, DISPONIBILIDAD(ENUM), FECHA_LIMITE, NOMBRE, PRECIO, UNIDADES, MODERADOR_CODIGO, USUARIO_CODIGO */
insert into producto values (1, "INACTIVO", "Reloj Inteligente con seguimiento de actividad, GPS y pantalla siempre activa", "2023-04-15", "2023-04-15", "Reloj Inteligente", 1500000, 30, 1, 1);
insert into producto values (2, "INACTIVO", "Balon rojo", "2023-04-15", "2023-04-15", "Reloj Inteligente", 1500000, 30, 1, 1);
insert into producto values (3, "INACTIVO", "Elefante", "2023-04-15", "2023-04-15", "es una elefante Inteligente", 1000, 30, 1, 1);
insert into producto values (4, "INACTIVO", "Balon de baloncesto", "2023-04-15", "2023-04-15", "es una Balon grande", 1000, 30, 1, 1);
insert into producto values (5, "INACTIVO", "computador", "2023-04-15", "2023-04-15", "es una estafa no lo compre", 1000, 30, 1, 1);



/*----------------------------------- Compra -------------------------------*/
/*         codigo , fecaha_creacion , medio_pago , valor_total , usuario_compra_codigo */
insert into compra values (1,"2023-04-15", "Efectivo", 12000,1);
insert into compra values (2,"2023-04-16", "Efectivo", 15000,1);
insert into compra values (3,"2023-04-16", "Efectivo", 15000,2);
insert into compra values (4,"2023-04-16", "Efectivo", 15000,1);
insert into compra values (5,"2023-04-16", "Efectivo", 15000,3);

/*----------------------------------- comentario -------------------------------*/
/* codigo , fecaha_creacion , mensaje , producto_comentario_codigo ,  usuario_comentario_codigo */
insert into comentario values (1,"2023-04-16", "primer comentario",2,1);
