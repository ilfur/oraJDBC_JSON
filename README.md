# oraJDBC_JSON
three little samples showing Oracle's new JDBC 20c embedded JSON support.
JSON Tables in Oracle can be read directly through JDBC, offering JSONP and JSONB support
without first converting between Oracle's internal "OSON" storage format and String.

The 20c JDBC Driver has built-in mapping classes for JSON table columns, just specify what You want
like String, Reader, OracleJsonDatum (native OSON) or a whole JsonParser and You'll get it. 
