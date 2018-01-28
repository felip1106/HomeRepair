package co.com.app.homerepair.model;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import co.com.app.homerepair.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

import co.com.app.homerepair.dao.EstadoSolicitudDao;
import co.com.app.homerepair.dao.SolicitudDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "ESTADO_SOLICITUD".
 */
@Entity(active = true)
public class EstadoSolicitud {

    @Id
    private Long id;
    private String estsol_descripcion;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient EstadoSolicitudDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "sol_estsol_id")
    })
    private List<Solicitud> solicitudes;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public EstadoSolicitud() {
    }

    public EstadoSolicitud(Long id) {
        this.id = id;
    }

    @Generated
    public EstadoSolicitud(Long id, String estsol_descripcion) {
        this.id = id;
        this.estsol_descripcion = estsol_descripcion;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEstadoSolicitudDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstsol_descripcion() {
        return estsol_descripcion;
    }

    public void setEstsol_descripcion(String estsol_descripcion) {
        this.estsol_descripcion = estsol_descripcion;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Solicitud> getSolicitudes() {
        if (solicitudes == null) {
            __throwIfDetached();
            SolicitudDao targetDao = daoSession.getSolicitudDao();
            List<Solicitud> solicitudesNew = targetDao._queryEstadoSolicitud_Solicitudes(id);
            synchronized (this) {
                if(solicitudes == null) {
                    solicitudes = solicitudesNew;
                }
            }
        }
        return solicitudes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetSolicitudes() {
        solicitudes = null;
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}