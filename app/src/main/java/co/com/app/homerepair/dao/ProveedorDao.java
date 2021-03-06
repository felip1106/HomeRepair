package co.com.app.homerepair.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import co.com.app.homerepair.model.ActividadEconomica;
import co.com.app.homerepair.model.Proveedor;
import co.com.app.homerepair.model.Usuarios;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "PROVEEDOR".
*/
public class ProveedorDao extends AbstractDao<Proveedor, Long> {

    public static final String TABLENAME = "PROVEEDOR";

    /**
     * Properties of entity Proveedor.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Prov_rzocial = new Property(1, String.class, "prov_rzocial", false, "PROV_RZOCIAL");
        public final static Property Prov_email = new Property(2, String.class, "prov_email", false, "PROV_EMAIL");
        public final static Property Prov_tel = new Property(3, String.class, "prov_tel", false, "PROV_TEL");
        public final static Property Prov_direccion = new Property(4, String.class, "prov_direccion", false, "PROV_DIRECCION");
        public final static Property Prov_estado = new Property(5, String.class, "prov_estado", false, "PROV_ESTADO");
        public final static Property Prov_ae_id = new Property(6, Long.class, "prov_ae_id", false, "PROV_AE_ID");
        public final static Property Prov_usu_id = new Property(7, Long.class, "prov_usu_id", false, "PROV_USU_ID");
    }

    private DaoSession daoSession;

    private Query<Proveedor> actividadEconomica_ProveedoresQuery;
    private Query<Proveedor> usuarios_ProveedoresQuery;

    public ProveedorDao(DaoConfig config) {
        super(config);
    }
    
    public ProveedorDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROVEEDOR\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"PROV_RZOCIAL\" TEXT," + // 1: prov_rzocial
                "\"PROV_EMAIL\" TEXT," + // 2: prov_email
                "\"PROV_TEL\" TEXT," + // 3: prov_tel
                "\"PROV_DIRECCION\" TEXT," + // 4: prov_direccion
                "\"PROV_ESTADO\" TEXT," + // 5: prov_estado
                "\"PROV_AE_ID\" INTEGER," + // 6: prov_ae_id
                "\"PROV_USU_ID\" INTEGER);"); // 7: prov_usu_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROVEEDOR\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Proveedor entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String prov_rzocial = entity.getProv_rzocial();
        if (prov_rzocial != null) {
            stmt.bindString(2, prov_rzocial);
        }
 
        String prov_email = entity.getProv_email();
        if (prov_email != null) {
            stmt.bindString(3, prov_email);
        }
 
        String prov_tel = entity.getProv_tel();
        if (prov_tel != null) {
            stmt.bindString(4, prov_tel);
        }
 
        String prov_direccion = entity.getProv_direccion();
        if (prov_direccion != null) {
            stmt.bindString(5, prov_direccion);
        }
 
        String prov_estado = entity.getProv_estado();
        if (prov_estado != null) {
            stmt.bindString(6, prov_estado);
        }
 
        Long prov_ae_id = entity.getProv_ae_id();
        if (prov_ae_id != null) {
            stmt.bindLong(7, prov_ae_id);
        }
 
        Long prov_usu_id = entity.getProv_usu_id();
        if (prov_usu_id != null) {
            stmt.bindLong(8, prov_usu_id);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Proveedor entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String prov_rzocial = entity.getProv_rzocial();
        if (prov_rzocial != null) {
            stmt.bindString(2, prov_rzocial);
        }
 
        String prov_email = entity.getProv_email();
        if (prov_email != null) {
            stmt.bindString(3, prov_email);
        }
 
        String prov_tel = entity.getProv_tel();
        if (prov_tel != null) {
            stmt.bindString(4, prov_tel);
        }
 
        String prov_direccion = entity.getProv_direccion();
        if (prov_direccion != null) {
            stmt.bindString(5, prov_direccion);
        }
 
        String prov_estado = entity.getProv_estado();
        if (prov_estado != null) {
            stmt.bindString(6, prov_estado);
        }
 
        Long prov_ae_id = entity.getProv_ae_id();
        if (prov_ae_id != null) {
            stmt.bindLong(7, prov_ae_id);
        }
 
        Long prov_usu_id = entity.getProv_usu_id();
        if (prov_usu_id != null) {
            stmt.bindLong(8, prov_usu_id);
        }
    }

    @Override
    protected final void attachEntity(Proveedor entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Proveedor readEntity(Cursor cursor, int offset) {
        Proveedor entity = new Proveedor( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // prov_rzocial
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // prov_email
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // prov_tel
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // prov_direccion
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // prov_estado
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // prov_ae_id
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // prov_usu_id
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Proveedor entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProv_rzocial(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setProv_email(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProv_tel(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setProv_direccion(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProv_estado(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setProv_ae_id(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setProv_usu_id(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Proveedor entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Proveedor entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Proveedor entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "proveedores" to-many relationship of ActividadEconomica. */
    public List<Proveedor> _queryActividadEconomica_Proveedores(Long prov_ae_id) {
        synchronized (this) {
            if (actividadEconomica_ProveedoresQuery == null) {
                QueryBuilder<Proveedor> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Prov_ae_id.eq(null));
                actividadEconomica_ProveedoresQuery = queryBuilder.build();
            }
        }
        Query<Proveedor> query = actividadEconomica_ProveedoresQuery.forCurrentThread();
        query.setParameter(0, prov_ae_id);
        return query.list();
    }

    /** Internal query to resolve the "proveedores" to-many relationship of Usuarios. */
    public List<Proveedor> _queryUsuarios_Proveedores(Long prov_usu_id) {
        synchronized (this) {
            if (usuarios_ProveedoresQuery == null) {
                QueryBuilder<Proveedor> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Prov_usu_id.eq(null));
                usuarios_ProveedoresQuery = queryBuilder.build();
            }
        }
        Query<Proveedor> query = usuarios_ProveedoresQuery.forCurrentThread();
        query.setParameter(0, prov_usu_id);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getActividadEconomicaDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getUsuariosDao().getAllColumns());
            builder.append(" FROM PROVEEDOR T");
            builder.append(" LEFT JOIN ACTIVIDAD_ECONOMICA T0 ON T.\"PROV_AE_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN USUARIOS T1 ON T.\"PROV_USU_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Proveedor loadCurrentDeep(Cursor cursor, boolean lock) {
        Proveedor entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ActividadEconomica actividadEconomica = loadCurrentOther(daoSession.getActividadEconomicaDao(), cursor, offset);
        entity.setActividadEconomica(actividadEconomica);
        offset += daoSession.getActividadEconomicaDao().getAllColumns().length;

        Usuarios usuarios = loadCurrentOther(daoSession.getUsuariosDao(), cursor, offset);
        entity.setUsuarios(usuarios);

        return entity;    
    }

    public Proveedor loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Proveedor> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Proveedor> list = new ArrayList<Proveedor>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Proveedor> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Proveedor> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
