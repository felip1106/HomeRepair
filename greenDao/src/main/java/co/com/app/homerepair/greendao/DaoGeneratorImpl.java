package co.com.app.homerepair.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

public class DaoGeneratorImpl {

    public static void main(String[] args) {
        try {
            Schema schema = new Schema(1, "co.com.app.homerepair.model");
            DaoGenerator daoGenerator = new DaoGenerator();

            //  add entities
            addEntities(schema);

            // DAO classes go into the "dao" package
            schema.setDefaultJavaPackageDao("co.com.app.homerepair.dao");
            // test classes go into the "test" package
            schema.setDefaultJavaPackageTest("co.com.app.homerepair.test");
            // make entities active
            schema.enableActiveEntitiesByDefault();
            // enable KEEP section support
            schema.enableKeepSectionsByDefault();

            daoGenerator.generateAll(schema, "../app/src/main/java");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addEntities(Schema schema) {
        //  add entities to schema
        Entity actividadEconomica = schema.addEntity("ActividadEconomica");
        Entity adjunto = schema.addEntity("Adjunto");
        Entity estadoSolicitud = schema.addEntity("EstadoSolicitud");
        Entity rol = schema.addEntity("Rol");
        Entity categoria = schema.addEntity("Categoria");
        Entity usuarios = schema.addEntity("Usuarios");
        Entity clientes = schema.addEntity("Clientes");
        Entity proveedor = schema.addEntity("Proveedor");
        Entity categoriaProveedor = schema.addEntity("CategoriaProveedor");
        Entity solicitud = schema.addEntity("Solicitud");
        Entity comentario = schema.addEntity("Comentario");
        Entity cotizacion = schema.addEntity("Cotizacion");
        Entity adjuntoSolicitud = schema.addEntity("AdjuntoSolicitud");

        //  add properties to an entity
        addActividadEconomicaProperties(actividadEconomica);
        addAdjuntoProperties(adjunto);
        addEstadoSolicitudProperties(estadoSolicitud);
        addRolProperties(rol);
        addCategoriaProperties(categoria);
        addUsuariosProperties(usuarios);
        addClientesProperties(clientes);
        addProveedorProperties(proveedor);
        addCategoriaProveedorProperties(categoriaProveedor);
        addSolicitudProperties(solicitud);
        addComentarioProperties(comentario);
        addCotizacionProperties(cotizacion);
        addAdjuntoSolicitudProperties(adjuntoSolicitud);

        //  add relations
        addUsuariosToRolRelation(usuarios, rol);
        addClientesToUsuariosRelation(clientes, usuarios);
        addProveedorToActividadEconomicaRelation(proveedor, actividadEconomica);
        addProveedorToUsuariosRelation(proveedor, usuarios);
        addCategoriaProveedorToCategoria(categoriaProveedor, categoria);
        addCategoriaProveedorToProveedor(categoriaProveedor, proveedor);
        addSolicitudToClientes(solicitud, clientes);
        addSolicitudToEstadoSolicitud(solicitud, estadoSolicitud);
        addSolicitudToCategoria(solicitud, categoria);
        addComentarioToSolicitud(comentario, solicitud);
        addComentarioToUsuarios(comentario, usuarios);
        addCotizacionToClientes(cotizacion, clientes);
        addCotizacionToProveedor(cotizacion, proveedor);
        addCotizacionToSolicitud(cotizacion, solicitud);
        addAdjuntoSolicitudToSolicitud(adjuntoSolicitud, solicitud);
        addAdjuntoSolicitudToAdjunto(adjuntoSolicitud, adjunto);

    }

    private static void addActividadEconomicaProperties(Entity actividadEconomica) {
        actividadEconomica.addIdProperty().primaryKey();
        actividadEconomica.addStringProperty("ae_descripcion");
        actividadEconomica.addStringProperty("ae_estado");
    }

    private static void addAdjuntoProperties(Entity adjunto) {
        adjunto.addIdProperty().primaryKey().autoincrement();
        adjunto.addByteArrayProperty("adj_img");
    }

    private static void addEstadoSolicitudProperties(Entity estadoSolicitud) {
        estadoSolicitud.addIdProperty().primaryKey();
        estadoSolicitud.addStringProperty("estsol_descripcion");
    }

    private static void addRolProperties(Entity rol) {
        rol.addIdProperty().primaryKey();
        rol.addStringProperty("rol_nombre");
        rol.addStringProperty("rol_estado");
    }

    private static void addCategoriaProperties(Entity categoria) {
        categoria.addIdProperty().primaryKey();
        categoria.addStringProperty("cat_descripcion");
        categoria.addStringProperty("cat_estado");
    }

    private static void addUsuariosProperties(Entity usuarios) {
        usuarios.addIdProperty().primaryKey().autoincrement();
        usuarios.addStringProperty("usu_nombre");
        usuarios.addStringProperty("usu_pass");
        usuarios.addByteArrayProperty("usu_img_perfil");
        usuarios.addStringProperty("usu_estado");
    }

    private static void addClientesProperties(Entity clientes) {
        clientes.addIdProperty().primaryKey();
        clientes.addStringProperty("cl_nombre").notNull();
        clientes.addStringProperty("cl_apellidos").notNull();
        clientes.addStringProperty("cl_email");
        clientes.addStringProperty("cl_telefono");
        clientes.addStringProperty("cl_direccion");
        clientes.addStringProperty("cl_fecha_naci");
        clientes.addStringProperty("cl_estado");
    }

    private static void addProveedorProperties(Entity proveedor) {
        proveedor.addIdProperty().primaryKey();
        proveedor.addStringProperty("prov_rzocial");
        proveedor.addStringProperty("prov_email");
        proveedor.addStringProperty("prov_tel");
        proveedor.addStringProperty("prov_direccion");
        proveedor.addStringProperty("prov_estado");
    }

    private static void addCategoriaProveedorProperties(Entity categoriaProveedor) {
        categoriaProveedor.addIdProperty().primaryKey();
    }

    private static void addSolicitudProperties(Entity solicitud) {
        solicitud.addIdProperty().primaryKey().autoincrement();
        solicitud.addStringProperty("sol_descripcion");
        solicitud.addIntProperty("sol_calificacion");
    }

    private static void addComentarioProperties(Entity comentario) {
        comentario.addIdProperty().primaryKey().autoincrement();
        comentario.addStringProperty("ctrio_descripcion");
    }

    private static void addCotizacionProperties(Entity cotizacion) {
        cotizacion.addIdProperty().primaryKey().autoincrement();
        cotizacion.addStringProperty("cot_valor");
        cotizacion.addStringProperty("cot_estado");
    }

    private static void addAdjuntoSolicitudProperties(Entity adjuntoSolicitud) {
        adjuntoSolicitud.addIdProperty().primaryKey();
    }

    private static void addUsuariosToRolRelation(Entity usuarios, Entity rol) {
        Property rolId = usuarios.addLongProperty("usu_rol_id").getProperty();
        usuarios.addToOne(rol, rolId);

        ToMany rolToUsuarios = rol.addToMany(usuarios, rolId);
        rolToUsuarios.setName("usuarios");
    }

    private static void addClientesToUsuariosRelation(Entity clientes, Entity usuarios) {
        Property usuariosId = clientes.addLongProperty("cl_usu_id").notNull().getProperty();
        clientes.addToOne(usuarios, usuariosId);

        ToMany usuariosToClientes = usuarios.addToMany(clientes, usuariosId);
        usuariosToClientes.setName("clientes");
    }

    private static void addProveedorToActividadEconomicaRelation(Entity proveedor, Entity actividadEconomica) {
        Property actividadEconomicaId = proveedor.addLongProperty("prov_ae_id").getProperty();
        proveedor.addToOne(actividadEconomica, actividadEconomicaId);

        ToMany actividadEconomicaToProveedores = actividadEconomica.addToMany(proveedor, actividadEconomicaId);
        actividadEconomicaToProveedores.setName("proveedores");
    }

    private static void addProveedorToUsuariosRelation(Entity proveedor, Entity usuarios) {
        Property usuariosId = proveedor.addLongProperty("prov_usu_id").getProperty();
        proveedor.addToOne(usuarios, usuariosId);

        ToMany usuariosToProveedores = usuarios.addToMany(proveedor, usuariosId);
        usuariosToProveedores.setName("proveedores");
    }

    private static void addCategoriaProveedorToCategoria(Entity categoriaProveedor, Entity categoria) {
        Property categoriaId = categoriaProveedor.addLongProperty("cat_id").index().unique().getProperty();
        categoriaProveedor.addToOne(categoria, categoriaId);

        ToMany categoriaToCategoriasProveedor = categoria.addToMany(categoriaProveedor, categoriaId);
        categoriaToCategoriasProveedor.setName("categoriasProveedor");
    }

    private static void addCategoriaProveedorToProveedor(Entity categoriaProveedor, Entity proveedor) {
        Property proveedorId = categoriaProveedor.addLongProperty("prov_id").index().unique().getProperty();
        categoriaProveedor.addToOne(proveedor, proveedorId);

        ToMany proveedorToCategoriasProveedor = proveedor.addToMany(categoriaProveedor, proveedorId);
        proveedorToCategoriasProveedor.setName("categoriasProveedor");
    }

    private static void addSolicitudToClientes(Entity solicitud, Entity clientes) {
        Property clientesId = solicitud.addLongProperty("sol_cl_id").getProperty();
        solicitud.addToOne(clientes, clientesId);

        ToMany clientesToSolicitudes = clientes.addToMany(solicitud, clientesId);
        clientesToSolicitudes.setName("solicitudes");
    }

    private static void addSolicitudToEstadoSolicitud(Entity solicitud, Entity estadoSolicitud) {
        Property estadoSolicitudId = solicitud.addLongProperty("sol_estsol_id").getProperty();
        solicitud.addToOne(estadoSolicitud, estadoSolicitudId);

        ToMany estadoSolicitudToSolicitudes = estadoSolicitud.addToMany(solicitud, estadoSolicitudId);
        estadoSolicitudToSolicitudes.setName("solicitudes");
    }

    private static void addSolicitudToCategoria(Entity solicitud, Entity categoria) {
        Property categoriaId = solicitud.addLongProperty("sol_cat_id").getProperty();
        solicitud.addToOne(categoria, categoriaId);

        ToMany categoriaToSolicitudes = categoria.addToMany(solicitud, categoriaId);
        categoriaToSolicitudes.setName("solicitudes");
    }

    private static void addComentarioToSolicitud(Entity comentario, Entity solicitud) {
        Property solicitudId = comentario.addLongProperty("ctrio_sol_id").getProperty();
        comentario.addToOne(solicitud, solicitudId);

        ToMany solicitudIdToComentarios = solicitud.addToMany(comentario, solicitudId);
        solicitudIdToComentarios.setName("comentarios");
    }

    private static void addComentarioToUsuarios(Entity comentario, Entity usuarios) {
        Property usuariosId = comentario.addLongProperty("ctrio_usu_id").getProperty();
        comentario.addToOne(usuarios, usuariosId);

        ToMany usuariosToComentarios = usuarios.addToMany(comentario, usuariosId);
        usuariosToComentarios.setName("comentario");
    }

    private static void addCotizacionToClientes(Entity cotizacion, Entity clientes) {
        Property clientesId = cotizacion.addLongProperty("cot_cl_id").notNull().getProperty();
        cotizacion.addToOne(clientes, clientesId);

        ToMany clientesToCotizaciones = clientes.addToMany(cotizacion, clientesId);
        clientesToCotizaciones.setName("cotizaciones");
    }

    private static void addCotizacionToProveedor(Entity cotizacion, Entity proveedor) {
        Property proveedorId = cotizacion.addLongProperty("cot_prov_id").getProperty();
        cotizacion.addToOne(proveedor, proveedorId);

        ToMany proveedorToCotizaciones = proveedor.addToMany(cotizacion, proveedorId);
        proveedorToCotizaciones.setName("cotizaciones");
    }

    private static void addCotizacionToSolicitud(Entity cotizacion, Entity solicitud) {
        Property solicitudId = cotizacion.addLongProperty("cot_sol_id").getProperty();
        cotizacion.addToOne(solicitud, solicitudId);

        ToMany solicitudToCotizaciones = solicitud.addToMany(cotizacion, solicitudId);
        solicitudToCotizaciones.setName("cotizaciones");
    }

    private static void addAdjuntoSolicitudToSolicitud(Entity adjuntoSolicitud, Entity solicitud) {
        Property solicitudId = adjuntoSolicitud.addLongProperty("sol_id").index().unique().getProperty();
        adjuntoSolicitud.addToOne(solicitud, solicitudId);

        ToMany solicitudToAdjuntosSolicitud = solicitud.addToMany(adjuntoSolicitud, solicitudId);
        solicitudToAdjuntosSolicitud.setName("adjuntosSolicitud");
    }

    private static void addAdjuntoSolicitudToAdjunto(Entity adjuntoSolicitud, Entity adjunto) {
        Property adjuntoId = adjuntoSolicitud.addLongProperty("adj_id").index().unique().getProperty();
        adjuntoSolicitud.addToOne(adjunto, adjuntoId);

        ToMany adjuntoToAdjuntosSolicitud = adjunto.addToMany(adjuntoSolicitud, adjuntoId);
        adjuntoToAdjuntosSolicitud.setName("adjuntosSolicitud");
    }

}
