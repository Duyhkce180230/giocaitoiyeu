import { NextRequest, NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/app/api/auth/[...nextauth]/route";
import {
  getAllLightCones,
  createLightCone,
  updateLightCone,
  deleteLightCone,
} from "@/dao/lightconeDao";

function requireAdmin(session: Awaited<ReturnType<typeof getServerSession>>) {
  if (!session || (session.user as any)?.role !== 1) {
    return NextResponse.json({ error: "Unauthorized - Admin only" }, { status: 403 });
  }
  return null;
}

// GET /api/lightcones — Equivalent to LightConeServlet doGet listLightCone
export async function GET() {
  const lightcones = await getAllLightCones();
  return NextResponse.json(lightcones);
}

// POST /api/lightcones — Equivalent to LightConeServlet doPost addLightCone
export async function POST(req: NextRequest) {
  const session = await getServerSession(authOptions);
  const authError = requireAdmin(session);
  if (authError) return authError;

  const data = await req.json();
  const lc = await createLightCone(data);
  return NextResponse.json(lc, { status: 201 });
}

// PUT /api/lightcones — Equivalent to LightConeServlet doPost editLightCone
export async function PUT(req: NextRequest) {
  const session = await getServerSession(authOptions);
  const authError = requireAdmin(session);
  if (authError) return authError;

  const { id, ...data } = await req.json();
  const lc = await updateLightCone(Number(id), data);
  return NextResponse.json(lc);
}

// DELETE /api/lightcones — Equivalent to LightConeServlet doPost deleteLightCone
export async function DELETE(req: NextRequest) {
  const session = await getServerSession(authOptions);
  const authError = requireAdmin(session);
  if (authError) return authError;

  const { id } = await req.json();
  await deleteLightCone(Number(id));
  return NextResponse.json({ success: true });
}