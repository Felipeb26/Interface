package com.batsworks.interfaces.database;

import com.batsworks.interfaces.utils.FormatString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class CustomRepository<T> implements Repository<T> {

    private static final Logger log = Logger.getLogger(CustomRepository.class.getName());

    private final Connection connection;
    final transient Function<ResultSet, T> rowMapper;
    private final String dbEntity;

    private CustomRepository() {
        throw new IllegalStateException("Utility class");
    }

    public CustomRepository(Class<T> t, Function<ResultSet, T> function) {
        this.dbEntity = FormatString.findEntityByClassName(t);
        this.connection = SQLiteConnection.connector();
        this.rowMapper = function;
    }


    public T custom(String query) {
        T t = null;

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                t = rowMapper.apply(rs);
            }
            return t;
        } catch (Exception e) {
            log.severe(e.getMessage());
            return null;
        }
    }

    @Override
    public T findById(Long id) {
        T t = null;
        String query = String.format("select * from %s where id=%s", dbEntity, id);

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while ((rs.next())) {
                t = rowMapper.apply(rs);
            }
            return t;
        } catch (Exception e) {
            log.severe(e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        List<T> ts = new ArrayList<>();
        String query = String.format("select * from %s", dbEntity);

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ts.add(rowMapper.apply(rs));
            }
            return ts;
        } catch (Exception e) {
            log.severe(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Boolean save(T t) {
        var insert = FormatString.formatStringToInsert(t.toString());
        String query = String.format("insert into %s (%s) values (%s)", dbEntity, insert.get("chave"),
                insert.get("valor"));

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.executeQuery();
            return true;
        } catch (Exception e) {
            log.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(Long aLong, T t) {
        var update = FormatString.formatStringToUpdate(t.toString());
        String query = String.format("update %s set %s where id=%d", dbEntity, update, aLong);

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.executeQuery();
            return true;
        } catch (Exception e) {
            log.severe(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        String query = String.format("delete from %s where id=%s", dbEntity, id);

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();
            return rs.rowDeleted();
        } catch (Exception e) {
            log.severe(e.getMessage());
            return false;
        }
    }


}
