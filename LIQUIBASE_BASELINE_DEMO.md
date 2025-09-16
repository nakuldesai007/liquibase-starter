# Liquibase Baseline and Table Updates Demo

This project demonstrates how to baseline existing tables with Liquibase and perform table updates in a production-like environment.

## 🎯 Demo Overview

This demo simulates a real-world scenario where:
1. **Baseline Phase**: Tables already exist in production and need to be brought under Liquibase control
2. **Update Phase**: New changes need to be applied to existing tables

## 📁 File Structure

```
src/main/resources/db/changelog/
├── db.changelog-master.xml                    # Main changelog file
└── changes/
    ├── 000-baseline-existing-tables.xml      # Baseline changelog (simulates existing production tables)
    ├── 001-initial-schema.xml               # Original schema (commented out for demo)
    ├── 002-sample-data.xml                  # Original sample data (commented out for demo)
    └── 003-demo-table-updates.xml           # Demo updates to existing tables
```

## 🚀 Running the Demo

### Step 1: Start the Application

```bash
mvn spring-boot:run
```

This will:
- Create the baseline tables (users, products, orders, order_items)
- Insert sample data
- Apply all the demo updates

### Step 2: Verify in pgAdmin 4

1. Open pgAdmin 4 (already launched)
2. Connect to your PostgreSQL database
3. Navigate to `liquibase_starter` database
4. Explore the tables and see the applied changes

## 📊 What Gets Created

### Baseline Tables (Simulating Existing Production)

1. **users** - User management
2. **products** - Product catalog  
3. **orders** - Order management
4. **order_items** - Order line items

### Demo Updates Applied

#### Users Table Updates:
- ✅ Added `phone_number` column
- ✅ Added `date_of_birth` column
- ✅ Added `address` column
- ✅ Added `is_active` boolean column

#### Products Table Updates:
- ✅ Added `sku` (Stock Keeping Unit) column
- ✅ Added `weight` column
- ✅ Added `is_featured` boolean column

#### Orders Table Updates:
- ✅ Added `tracking_number` column
- ✅ Added `shipping_address` column
- ✅ Added `notes` column
- ✅ Added `shipped_date` column

#### Order Items Table Updates:
- ✅ Added `discount_percentage` column
- ✅ Added `discount_amount` column

#### New Table Created:
- ✅ **user_preferences** - User preference settings

#### Performance Improvements:
- ✅ Created indexes on frequently queried columns
- ✅ Added foreign key constraints

## 🔍 Viewing the Results

### In pgAdmin 4:
1. **Tables**: See all created/updated tables
2. **Data**: View sample data in each table
3. **Schema**: Check column definitions and constraints
4. **Indexes**: View performance indexes

### Database Queries to Try:

```sql
-- View all users with their new columns
SELECT username, email, phone_number, is_active 
FROM users;

-- View products with new features
SELECT name, price, sku, is_featured, category 
FROM products 
WHERE is_featured = true;

-- View user preferences
SELECT u.username, p.preference_key, p.preference_value
FROM users u
JOIN user_preferences p ON u.id = p.user_id;

-- View orders with tracking information
SELECT o.id, u.username, o.status, o.tracking_number, o.total_amount
FROM orders o
JOIN users u ON o.user_id = u.id;
```

## 🛠️ Liquibase Commands for Real-World Scenarios

### For Existing Production Databases:

```bash
# Mark existing tables as applied without running changesets
mvn liquibase:changelogSync

# Check what changes would be applied
mvn liquibase:status

# Generate changelog from existing database
mvn liquibase:generateChangeLog

# Rollback last changeset
mvn liquibase:rollback -Dliquibase.rollbackCount=1

# Update to specific tag
mvn liquibase:rollback -Dliquibase.rollbackTag=v1.0
```

## 📋 Key Liquibase Features Demonstrated

### 1. **Preconditions**
```xml
<preConditions onFail="MARK_RAN">
    <not>
        <tableExists tableName="users"/>
    </not>
</preConditions>
```

### 2. **Column Additions**
```xml
<addColumn tableName="users">
    <column name="phone_number" type="VARCHAR(20)">
        <constraints nullable="true"/>
    </column>
</addColumn>
```

### 3. **Data Updates**
```xml
<update tableName="users">
    <column name="phone_number" value="+1-555-0123"/>
    <where>username = 'admin'</where>
</update>
```

### 4. **Index Creation**
```xml
<createIndex tableName="users" indexName="idx_users_email">
    <column name="email"/>
</createIndex>
```

### 5. **Foreign Key Constraints**
```xml
<addForeignKeyConstraint baseTableName="orders" baseColumnNames="user_id"
                        referencedTableName="users" referencedColumnNames="id"
                        constraintName="fk_orders_user"/>
```

## 🎯 Real-World Application

This demo pattern is used when:
- **Migrating existing applications** to Liquibase
- **Adding new features** to production systems
- **Database schema evolution** over time
- **Maintaining data integrity** during updates

## 🔧 Configuration

The application is configured to use PostgreSQL with:
- **Database**: `liquibase_starter`
- **Host**: `localhost:5432`
- **Username**: `postgres`
- **Password**: (none set)

All configuration is in `src/main/resources/application.yml`.

## 📚 Next Steps

Try modifying the demo by:
1. Adding new columns to existing tables
2. Creating additional tables
3. Adding more complex constraints
4. Implementing data migrations
5. Using Liquibase rollback features

---

**Happy Database Versioning! 🚀**
