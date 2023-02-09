package lk.ijse.tailorshop.dao.custom.impl;



import lk.ijse.tailorshop.dao.custom.MeasurementDAO;
import lk.ijse.tailorshop.dao.util.DBUtil;
import lk.ijse.tailorshop.entity.Measurement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MeasurementDAOImpl implements MeasurementDAO {
    @Override
    public boolean save(Measurement entity) throws SQLException {
        return DBUtil.executeUpdate("INSERT INTO Measurement VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                entity.getMeasurementId(),
                entity.getDetails(),
                entity.getLength(),
                entity.getNeck(),
                entity.getBust(),
                entity.getDownAShoulder(),
                entity.getUnderBust(),
                entity.getSleeve(),
                entity.getWest(),
                entity.getChest(),
                entity.getCrotch(),
                entity.getKnee(),
                entity.getCalf(),
                entity.getHipAndWestDistance(),
                entity.getInseam(),
                entity.getThigh(),
                entity.getHip(),
                entity.getSkirtLength(),
                entity.getFrockLength()
        );
    }

    @Override
    public boolean update(Measurement entity) throws SQLException {
        return DBUtil.executeUpdate("UPDATE Measurement SET details = ?, length = ?, neck = ?, bust = ?, downAShoulder = ?, underBust = ?, sleeve = ?, west = ?, chest = ?, crotch = ?, knee = ?, calf = ?, hipAndWestDistance = ?, inseam = ?, thigh = ?, hip = ?, skirtLength = ?, frockLength = ?  WHERE measurementId =?",
                entity.getDetails(),
                entity.getLength(),
                entity.getNeck(),
                entity.getBust(),
                entity.getDownAShoulder(),
                entity.getUnderBust(),
                entity.getSleeve(),
                entity.getWest(),
                entity.getChest(),
                entity.getCrotch(),
                entity.getKnee(),
                entity.getCalf(),
                entity.getHipAndWestDistance(),
                entity.getInseam(),
                entity.getThigh(),
                entity.getHip(),
                entity.getSkirtLength(),
                entity.getFrockLength(),
                entity.getMeasurementId()
        );
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException {
        return  DBUtil.executeUpdate("Delete From Measurement where measurementId=?",pk);
    }

    @Override
    public List<Measurement> findAll() throws SQLException {
        List<Measurement> measurementArrayList = new ArrayList<>();
        ResultSet rst= DBUtil.executeQuery("SELECT * FROM Measurement");
        while (rst.next()){
            measurementArrayList.add(getMeasurement(rst));
        }
        return measurementArrayList;
    }

    @Override
    public Optional<Measurement> findByPk(String pk) throws SQLException {
        ResultSet rst = DBUtil.executeQuery("SELECT * FROM Measurement WHERE measurementId = ?",pk);
        if (rst.next()){
            return Optional.of(getMeasurement(rst));
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findLastPk() throws SQLException {
        ResultSet rst= DBUtil.executeQuery("SELECT measurementId FROM Measurement ORDER BY measurementId DESC LIMIT 1");
        if(rst.next()){
            return Optional.of(rst.getString(1));
        }
        return Optional.empty();
    }

    @Override
    public long count() throws SQLException {
        ResultSet rst  = DBUtil.executeQuery("SELECT COUNT(measurementId) AS count FROM Measurement");
        rst.next();
        return rst.getInt(1);
    }

    private Measurement getMeasurement(ResultSet rst) throws SQLException {
        return new Measurement(
                rst.getString(1),
                rst.getString(2),
                rst.getDouble(3),
                rst.getDouble(4),
                rst.getDouble(5),
                rst.getDouble(6),
                rst.getDouble(7),
                rst.getDouble(8),
                rst.getDouble(9),
                rst.getDouble(10),
                rst.getDouble(11),
                rst.getDouble(12),
                rst.getDouble(13),
                rst.getDouble(14),
                rst.getDouble(15),
                rst.getDouble(16),
                rst.getDouble(17),
                rst.getDouble(18),
                rst.getDouble(19)
        );
    }
}
