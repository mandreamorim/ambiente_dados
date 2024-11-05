package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementResultSet {
    public ResultSet resultSet;
    public PreparedStatement statement;

    public StatementResultSet(ResultSet resultSet, PreparedStatement statement) {
        this.resultSet = resultSet;
        this.statement = statement;
    }

    public void closeAll(){
        try{
            resultSet.close();
            statement.close();
        } catch (SQLException ignored) {

        }
    }
}
