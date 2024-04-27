package br.fintrack.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "investiment")
public class Investiment {
    private Short id;
    private Short userId;
    private BigDecimal Value;
    private String investimentClass;
    private String status;
    private Date date;

    public Investiment() {
        super();
    }

    public Investiment(Short id, Short userId, BigDecimal value, String investimentClass, String status, Date date) {
        super();
        this.id = id;
        this.userId = userId;
        Value = value;
        this.investimentClass = investimentClass;
        this.status = status;
        this.date = date;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    @DynamoDBAttribute
    public Short getUserId() {
        return userId;
    }

    public void setUserId(Short userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute
    public BigDecimal getValue() {
        return Value;
    }

    public void setValue(BigDecimal value) {
        Value = value;
    }

    @DynamoDBAttribute
    public String getInvestimentClass() {
        return investimentClass;
    }

    public void setInvestimentClass(String investimentClass) {
        this.investimentClass = investimentClass;
    }

    @DynamoDBAttribute
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DynamoDBAttribute
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investiment that = (Investiment) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(Value, that.Value) && Objects.equals(investimentClass, that.investimentClass) && Objects.equals(status, that.status) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, Value, investimentClass, status, date);
    }

    @Override
    public String toString() {
        return "Investiment{" +
                "id=" + id +
                ", userId=" + userId +
                ", Value=" + Value +
                ", investimentClass='" + investimentClass + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
