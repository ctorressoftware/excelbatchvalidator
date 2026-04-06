# Excel Batch Validator

A Spring Boot service that validates Excel data concurrently using virtual threads, while aggregating results and maintaining structured validation output.

---

## Overview

This project processes Excel files containing structured person data and validates each row independently.

Instead of validating rows sequentially, it:

- distributes validation tasks across concurrent workers
- validates them in parallel
- aggregates results into a unified report

---

## Architecture

The validation flow is structured in clear stages:

Excel File  
↓  
ExcelDataExtractor (raw extraction)  
↓  
ExcelDataParser (transform → domain objects)  
↓  
ValidationItem builder  
↓  
DataValidatorCore (concurrent validation)  
↓  
ValidationReport (final output)

---

## Core Components

### ExcelDataValidationService

Entry point of the validation process:

- extracts raw data
- transforms into domain objects (Person)
- builds validation items
- delegates to the core validator

---

### DataValidatorCore

Responsible for concurrent processing:

- splits validation work into batches
- creates workers via factory
- executes using virtual threads
- collects results using ExecutorCompletionService

Uses:

Executors.newVirtualThreadPerTaskExecutor()

---

### BatchValidationWorker

Each worker:

- processes a batch of rows
- applies validators per field
- updates shared ValidationStats
- returns a ValidationResume

---

### Validators

Validation is modular and extensible:

- NameValidator
- LastnameValidator
- EmailValidator
- PhoneNumberValidator
- DniValidator
- GenderValidator

Each validator focuses on a single concern.

---

## Output

The system produces a ValidationReport containing:

- aggregated statistics (ValidationStats)
- per-batch results (ValidationResume)
- error collection per row

---

## Concurrency Model

- one worker per validation task (row/person)
- each worker runs in its own virtual thread
- results are collected as they complete
- final aggregation ensures consistency

---

## Tech Stack

- Java 21
- Spring Boot
- Apache POI
- Virtual Threads (Project Loom)

---

## Running the Application

./mvnw spring-boot:run

---

## API Usage

POST /file/validate
Content-Type: multipart/form-data

---

## Response

The endpoint returns a structured result wrapped in a generic response:

- success flag
- validation report
- aggregated statistics
- row-level validation results

## Example Data

Expected columns:

- name
- lastName
- email
- phone
- dni
- gender

Supports datasets with:

- valid rows
- invalid rows
- mixed validation cases

---

## Testing Strategy

The system is designed to validate:

- fully valid datasets
- datasets with multiple error types
- mixed datasets (valid + invalid rows)

The project includes a sample Excel file for testing the validation endpoint:

`src/test/resources/people-mixed-validation-cases.xlsx`

Example request:

```bash
curl -X POST http://localhost:8080/file/validate \
  -F "file=@src/test/resources/people-mixed-validation-cases.xlsx"
```
---

## Design Highlights

- clear separation of concerns
- modular validation per field
- batch-based processing
- concurrent execution with virtual threads
- consistent result aggregation

---

## Problem Solved

Sequential validation is slow.

Naive parallelization introduces inconsistency.

This project achieves:

- parallel validation
- controlled execution
- deterministic result aggregation