import { prisma } from "@/lib/prisma";

/** Tương đương ElementDAO.getAllElement() */
export async function getAllElements() {
  return prisma.element.findMany({ orderBy: { elementName: "asc" } });
}