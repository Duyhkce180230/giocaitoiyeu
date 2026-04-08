/**
 * Database provider switcher
 * Điều khiển bằng biến môi trường DB_PROVIDER:
 *   - "local"    → SQLite qua Prisma (mặc định dev)
 *   - "mssql"    → SQL Server qua Prisma (giữ nguyên DB cũ)
 *   - "firebase" → Firebase Firestore
 */

export type DbProvider = "local" | "firebase" | "mssql";

export function getDbProvider(): DbProvider {
  const provider = process.env.DB_PROVIDER as DbProvider;
  if (provider === "firebase" || provider === "mssql") return provider;
  return "local";
}

export function isFirebase(): boolean {
  return getDbProvider() === "firebase";
}

export function isPrisma(): boolean {
  return getDbProvider() !== "firebase";
}

/**
 * Trả về Prisma datasource provider tương ứng
 */
export function getPrismaProvider(): "sqlite" | "sqlserver" | "postgresql" {
  const provider = getDbProvider();
  if (provider === "mssql") return "sqlserver";
  return "sqlite"; // local default
}