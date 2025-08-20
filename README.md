# Walmart Data Processing and Integration Project

This repository contains a collection of projects developed to support the Walmart shipping and data processing teams. It includes four different components:

---

## ⚙️ Power-of-Two Max Heap (Java)

A custom priority queue implementation where each parent node has `2^k` children (parameterized in the constructor).

The structure is designed for benchmark testing across different branching factors.

---

## 🔄 Reconfigurable Data Processor (UML)

A flexible, mode-driven data processing pipeline that can switch between:
- **Dump mode** (discard data)
- **Passthrough mode** (insert into active DB)
- **Validate mode** (validate + insert)

Supports the following databases:
- Postgres  
- Redis  
- Elastic

A `configure(mode, database)` method sets the pipeline behavior. A `process(datapoint)` method processes data depending on the current configuration.

---

## 🐾 Pet Department ERD (Database Design)

A normalized relational schema used to store:
- Pet food  
- Pet toys  
- Pet apparel  
- Manufacturers  
- Animals  
- Customers and their transactions  
- Shipments between Walmart locations (origin, destination, products + quantity)

---

## 📦 Shipping Data ETL Script (Python)

Script reads several spreadsheets from [`forage-walmart-task-4`](https://github.com/theforage/forage-walmart-task-4), extracts the needed fields, munges the data into a normalized format, and uploads it into an SQLite database.

- Spreadsheet 0 → load directly  
- Spreadsheet 1 + 2 → combine by shipping ID, determine quantities, and insert as individual product records

---

## ✅ Getting Started

1. Clone the repo  
2. Run the Java heap implementation under `/heap/`  
3. Review the UML diagram in `/uml/` (PDF)  
4. Review the ERD diagram in `/erd/` (PDF)  
5. Run the Python ETL script under `/etl/`

```bash
python3 etl/insert_shipping_data.py
