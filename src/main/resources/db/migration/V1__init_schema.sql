-- UUID 생성 함수
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- 브랜드
CREATE TABLE IF NOT EXISTS brand (
                                     id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(255) NOT NULL,
    category   TEXT NOT NULL CHECK (category IN ('cafe','restaurant','bakery','etc')),
    image_url  TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP
    );
CREATE UNIQUE INDEX IF NOT EXISTS ux_brand_name ON brand(name);

-- 메뉴
CREATE TABLE IF NOT EXISTS menu (
                                    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    brand_id   UUID NOT NULL REFERENCES brand(id) ON DELETE CASCADE,
    name       VARCHAR(255) NOT NULL,
    price      INT CHECK (price IS NULL OR price >= 0),
    is_active  BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP
    );
CREATE UNIQUE INDEX IF NOT EXISTS ux_menu_brand_name ON menu(brand_id, name);
CREATE INDEX IF NOT EXISTS ix_menu_name ON menu(name);

-- 영양정보(사이즈별)
CREATE TABLE IF NOT EXISTS nutrition (
                                         id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    menu_id       UUID NOT NULL REFERENCES menu(id) ON DELETE CASCADE,
    size          TEXT NOT NULL,
    calories      INT  NOT NULL CHECK (calories >= 0),
    protein       NUMERIC(6,1) CHECK (protein      IS NULL OR protein      >= 0),
    fat           NUMERIC(6,1) CHECK (fat          IS NULL OR fat          >= 0),
    carbohydrate  NUMERIC(6,1) CHECK (carbohydrate IS NULL OR carbohydrate >= 0),
    sugar         NUMERIC(6,1) CHECK (sugar        IS NULL OR sugar        >= 0),
    caffeine_mg   INT CHECK (caffeine_mg IS NULL OR caffeine_mg >= 0),
    created_at    TIMESTAMP NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP
    );
CREATE UNIQUE INDEX IF NOT EXISTS ux_nutrition_menu_size ON nutrition(menu_id, size);
CREATE INDEX IF NOT EXISTS ix_nutrition_menu ON nutrition(menu_id);