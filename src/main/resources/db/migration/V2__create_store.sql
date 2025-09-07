CREATE TABLE store (
                       id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       brand_id   UUID NOT NULL REFERENCES brand(id),
                       name       TEXT NOT NULL,
                       lat        DOUBLE PRECISION NOT NULL,
                       lon        DOUBLE PRECISION NOT NULL,
                       address    TEXT,
                       created_at TIMESTAMP NOT NULL DEFAULT now(),
                       updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX IF NOT EXISTS ix_store_brand   ON store(brand_id);
CREATE INDEX IF NOT EXISTS ix_store_lat_lon ON store(lat, lon);