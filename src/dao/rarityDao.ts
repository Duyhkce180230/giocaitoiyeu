import { prisma } from "@/lib/prisma";

/** Tương đương RarityDAO.getAllRarity() */
export async function getAllRarities() {
  return prisma.rarity.findMany({ orderBy: { starName: "desc" } });
}